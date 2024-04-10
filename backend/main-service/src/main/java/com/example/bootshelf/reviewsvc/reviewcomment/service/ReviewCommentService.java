package com.example.bootshelf.reviewsvc.reviewcomment.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.BoardCommentException;
import com.example.bootshelf.common.error.entityexception.ReviewCommentException;
import com.example.bootshelf.common.error.entityexception.ReviewException;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import com.example.bootshelf.reviewsvc.review.repository.ReviewRepository;
import com.example.bootshelf.reviewsvc.reviewcomment.model.entity.ReviewComment;
import com.example.bootshelf.reviewsvc.reviewcomment.model.request.PatchUpdateReviewCommentReq;
import com.example.bootshelf.reviewsvc.reviewcomment.model.request.PostCreateReviewCommentReq;
import com.example.bootshelf.reviewsvc.reviewcomment.model.request.PostCreateReviewReplyReq;
import com.example.bootshelf.reviewsvc.reviewcomment.model.response.GetListReviewCommentRes;
import com.example.bootshelf.reviewsvc.reviewcomment.model.response.PatchUpdateReviewCommentRes;
import com.example.bootshelf.reviewsvc.reviewcomment.model.response.PostCreateReviewCommentRes;
import com.example.bootshelf.reviewsvc.reviewcomment.model.response.PostCreateReviewReplyRes;
import com.example.bootshelf.reviewsvc.reviewcomment.repository.ReviewCommentRepository;
import com.example.bootshelf.reviewsvc.reviewcommentup.model.entity.ReviewCommentUp;
import com.example.bootshelf.reviewsvc.reviewcommentup.repository.ReviewCommentUpRepository;
import com.example.bootshelf.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewCommentService {
    private final ReviewCommentRepository reviewCommentRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewCommentUpRepository reviewCommentUpRepository;

    // 댓글 작성
    @Transactional(readOnly = false)
    public BaseRes createReviewComment(User user, Integer reviewIdx, PostCreateReviewCommentReq postCreateReviewCommentReq) {

        Optional<Review> findReview = reviewRepository.findByIdx(reviewIdx);

        // 댓글을 작성하려는 후기글이 존재하지 않을 때
        if (!findReview.isPresent()) {
            throw new BoardCommentException(ErrorCode.REVIEW_COMMENT_NOT_EXISTS, String.format("Board with idx %d not found.", reviewIdx));
        }

        // 댓글의 내용이 비어있을 때
        if (postCreateReviewCommentReq.getReviewCommentContent() == null || postCreateReviewCommentReq.getReviewCommentContent().isEmpty()) {
            throw new ReviewCommentException(ErrorCode.INVALID_INPUT_VALUE, String.format("Content is empty."));
        }

        Review review = findReview.get();
        ReviewComment reviewComment = ReviewComment.builder()
                .review(review)
                .user(user)
                .reviewCommentContent(postCreateReviewCommentReq.getReviewCommentContent())
                .status(true)
                .upCnt(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        reviewCommentRepository.save(reviewComment);
        review.increaseCommentUpCnt();
        reviewRepository.save(review);

        PostCreateReviewCommentRes postCreateReviewCommentRes = PostCreateReviewCommentRes.builder()
                .idx(reviewComment.getIdx())
                .reviewCommentContent(postCreateReviewCommentReq.getReviewCommentContent())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("댓글 등록 성공")
                .result(postCreateReviewCommentRes)
                .build();

        return baseRes;

    }

    // 댓글/대댓글 조회
    @Transactional(readOnly = true)
    public BaseRes listComment(Integer reviewIdx) {
        List<ReviewComment> topLevelComments = reviewCommentRepository.findByReviewIdxAndParentIsNull(reviewIdx);
        List<GetListReviewCommentRes> response = new ArrayList<>();

        for (ReviewComment topLevelComment : topLevelComments) {
            GetListReviewCommentRes topLevelResponse = buildResponseWithChildren(topLevelComment);
            response.add(topLevelResponse);
        }

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("요청 성공")
                .result(response)
                .build();

        return baseRes;
    }

    private GetListReviewCommentRes buildResponseWithChildren(ReviewComment reviewComment) {
        GetListReviewCommentRes getListReviewCommentRes = GetListReviewCommentRes.builder()
                .idx(reviewComment.getIdx())
                .reviewIdx(reviewComment.getReview().getIdx())
                .userIdx(reviewComment.getUser().getIdx())
                .userNickName(reviewComment.getUser().getNickName())
                .userImg(reviewComment.getUser().getProfileImage())
                .reviewCommnetContent(reviewComment.getReviewCommentContent())
                .status(reviewComment.getStatus())
                .createAt(reviewComment.getCreatedAt())
                .updateAt(reviewComment.getUpdatedAt())
                .build();

        List<GetListReviewCommentRes> childrenResponses = new ArrayList<>();
        buildChildrenResponses(reviewComment, childrenResponses);
        getListReviewCommentRes.setChildren(childrenResponses);

        return getListReviewCommentRes;
    }

    private void buildChildrenResponses(ReviewComment parent, List<GetListReviewCommentRes> childrenResponses) {
        List<ReviewComment> childrenComments = parent.getChildren();
        if (childrenComments != null && !childrenComments.isEmpty()) {
            for (ReviewComment childComment : childrenComments) {
                GetListReviewCommentRes childResponse = buildResponseWithChildren(childComment);
                childrenResponses.add(childResponse);
            }
        }
    }

    // 댓글/대댓글 수정
    @Transactional(readOnly = false)
    public BaseRes updateComment(User user, Integer reviewIdx, Integer idx, PatchUpdateReviewCommentReq patchUpdateReviewCommentReq) {
        Optional<ReviewComment> result = reviewCommentRepository.findByIdxAndUserIdx(idx, user.getIdx());

        // 수정하고자 하는 댓글/대댓글을 찾지 못할 때
        if (!result.isPresent()) {
            throw new ReviewException(ErrorCode.REVIEW_COMMENT_NOT_EXISTS, String.format("ReviewCommentIdx [ idx : %s ] is not exists.", idx));
        }

        // 수정할 내용이 비어있을 때
        if (result.isPresent()) {
            if (patchUpdateReviewCommentReq.getReviewCommentContent() == null || patchUpdateReviewCommentReq.getReviewCommentContent().isEmpty()) {
                throw new ReviewCommentException(ErrorCode.INVALID_INPUT_VALUE, String.format("Content is empty."));
            }

            ReviewComment reviewComment = result.get();
            reviewComment.setUpdatedAt(LocalDateTime.now());
            reviewComment.setReviewCommentContent(patchUpdateReviewCommentReq.getReviewCommentContent());

            reviewCommentRepository.save(reviewComment);

            PatchUpdateReviewCommentRes patchUpdateReviewCommentRes = PatchUpdateReviewCommentRes.builder()
                    .idx(reviewComment.getIdx())
                    .userIdx(reviewComment.getUser().getIdx())
                    .nickName(reviewComment.getUser().getNickName())
                    .reviewCommentContent(reviewComment.getReviewCommentContent())
                    .createAt(reviewComment.getCreatedAt())
                    .updateAt(reviewComment.getUpdatedAt())
                    .build();

            BaseRes baseRes = BaseRes.builder()
                    .isSuccess(true)
                    .message("댓글 수정 성공")
                    .result(patchUpdateReviewCommentRes)
                    .build();

            return baseRes;
        }
        return null;
    }


    // 댓글/대댓글 삭제
    @Transactional(readOnly = false)
    public BaseRes deleteComment(User user, Integer idx) {
        Optional<ReviewComment> result = reviewCommentRepository.findByIdxAndUserIdx(idx, user.getIdx());

        // 삭제하고자 하는 댓글을 찾지 못할 때
        if (result.equals(0)) {
            throw new ReviewException(ErrorCode.REVIEW_COMMENT_NOT_EXISTS, String.format("ReviewCommentIdx [ idx : %s ] is not exists.", idx));
        }

        ReviewComment reviewComment = result.get();
        Review review = reviewComment.getReview();

        List<ReviewCommentUp> reviewCommentUps = reviewCommentUpRepository.findByReviewCommentIdx(idx);
        for (ReviewCommentUp reviewCommentUp : reviewCommentUps) {
            reviewCommentUp.setReviewComment(null);
            reviewCommentUp.setStatus(false);
            reviewCommentUpRepository.save(reviewCommentUp);
        }

        if (reviewComment.getParent() == null) {
            // 하위 댓글들도 함께 삭제
            deleteChildrenComments(reviewComment.getChildren());
        }

        reviewComment.setStatus(false);
        reviewCommentRepository.save(reviewComment);

        review.decreaseCommentUpCnt();
        reviewRepository.save(review);

        return BaseRes.builder()
                .isSuccess(true)
                .message("댓글 삭제 성공")
                .result("후기글 댓글 삭제 성공")
                .build();
    }

    private void deleteChildrenComments(List<ReviewComment> children) {
        if (children != null && !children.isEmpty()) {
            for (ReviewComment childComment : children) {
                // 재귀적으로 하위 댓글 삭제
                deleteChildrenComments(childComment.getChildren());
                childComment.setStatus(false);
                reviewCommentRepository.save(childComment);

            }
        }
    }


    // 대댓글 작성
    @Transactional(readOnly = false)
    public BaseRes createReviewReply(User user, Integer reviewIdx, Integer parentIdx, PostCreateReviewReplyReq postCreateReviewReplyReq) {

        Optional<ReviewComment> parentReviewComment = reviewCommentRepository.findById(parentIdx);
        Optional<Review> findReviw = reviewRepository.findByIdx(reviewIdx);

        // 상위댓글이 없을 때
        if (parentReviewComment.equals(0)) {
            throw new ReviewException(ErrorCode.REVIEW_COMMENT_NOT_EXISTS, String.format("parentIdx [ idx : %s ] is not exists.", parentIdx));
        }

        // 대댓글의 내용이 비어있을 때
        if (postCreateReviewReplyReq.getReviewReplyContent() == null || postCreateReviewReplyReq.getReviewReplyContent().isEmpty()) {
            throw new ReviewCommentException(ErrorCode.INVALID_INPUT_VALUE, String.format("Content is empty."));
        }

        Review review = findReviw.get();
        ReviewComment childrenReviewComment = ReviewComment.builder()
                .review(Review.builder().idx(reviewIdx).build())
                .user(user)
                .parent(parentReviewComment.get())  // 부모 댓글 설정
                .reviewCommentContent(postCreateReviewReplyReq.getReviewReplyContent())
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        parentReviewComment.get().getChildren().add(childrenReviewComment);
        reviewCommentRepository.save(childrenReviewComment);

        review.increaseCommentUpCnt();
        reviewRepository.save(review);

        PostCreateReviewReplyRes postCreateReviewReplyRes = PostCreateReviewReplyRes.builder()
                .parentIdx(childrenReviewComment.getParent().getIdx())
                .reviewCommentContent(postCreateReviewReplyReq.getReviewReplyContent())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("댓글 등록 성공")
                .result(postCreateReviewReplyRes)
                .build();

        return baseRes;
    }
}
