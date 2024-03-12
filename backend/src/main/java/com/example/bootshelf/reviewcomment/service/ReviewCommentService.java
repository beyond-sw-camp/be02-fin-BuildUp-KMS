package com.example.bootshelf.reviewcomment.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.review.exception.ReviewException;
import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.review.model.response.GetListCommentReviewRes;
import com.example.bootshelf.reviewcomment.exception.ReviewCommentException;
import com.example.bootshelf.reviewcomment.model.entity.ReviewComment;
import com.example.bootshelf.reviewcomment.model.request.PatchUpdateReviewCommentReq;
import com.example.bootshelf.reviewcomment.model.request.PostCreateReviewReplyReq;
import com.example.bootshelf.reviewcomment.model.response.GetListReviewCommentRes;
import com.example.bootshelf.reviewcomment.model.response.PatchUpdateReviewCommentRes;
import com.example.bootshelf.reviewcomment.model.response.PostCreateReviewCommentRes;
import com.example.bootshelf.reviewcomment.model.response.PostCreateReviewReplyRes;
import com.example.bootshelf.reviewcomment.repository.ReviewCommentRepository;
import com.example.bootshelf.reviewcomment.model.request.PostCreateReviewCommentReq;
import com.example.bootshelf.reviewscrap.exception.ReviewScrapException;
import com.example.bootshelf.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewCommentService {
    private final ReviewCommentRepository reviewCommentRepository;

    // 댓글 작성
    @Transactional(readOnly = false)
    public BaseRes createReviewComment(User user, Integer reviewIdx, PostCreateReviewCommentReq postCreateReviewCommentReq) {

        // 댓글의 내용이 비어있을 때
        if (postCreateReviewCommentReq.getReviewCommentContent() == null || postCreateReviewCommentReq.getReviewCommentContent().isEmpty()) {
            throw new ReviewCommentException(ErrorCode.INVALID_INPUT_VALUE, String.format("Content is empty."));
        }
            reviewCommentRepository.save(ReviewComment.builder()
                    .review(Review.builder().idx(reviewIdx).build())
                    .user(user)
                    .reviewCommentContent(postCreateReviewCommentReq.getReviewCommentContent())
                    .status(true)
                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build());

            PostCreateReviewCommentRes postCreateReviewCommentRes = PostCreateReviewCommentRes.builder()
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
                .reviewCommnetContent(reviewComment.getReviewCommentContent())
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
        Optional<ReviewComment> result = reviewCommentRepository.findByIdx(idx);

        // 수정하고자 하는 댓글/대댓글을 찾지 못할 때
        if (!result.isPresent()) {
            throw new ReviewException(ErrorCode.COMMENT_NOT_EXISTS, String.format("ReviewCommentIdx [ idx : %s ] is not exists.", idx));
        }

        // 수정할 내용이 비어있을 때
        if (result.isPresent()) {
            if (patchUpdateReviewCommentReq.getReviewCommentContent() == null || patchUpdateReviewCommentReq.getReviewCommentContent().isEmpty()) {
                throw new ReviewCommentException(ErrorCode.INVALID_INPUT_VALUE, String.format("Content is empty."));
            }

            ReviewComment reviewComment = result.get();
            reviewComment.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
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
    public BaseRes deleteComment(Integer idx, User user) {
        Optional<ReviewComment> result = reviewCommentRepository.findByIdx(idx);

        // 삭제하고자 하는 댓글을 찾지 못할 때
        if (result.equals(0)) {
            throw new ReviewException(ErrorCode.COMMENT_NOT_EXISTS, String.format("ReviewCommentIdx [ idx : %s ] is not exists.", idx));
        }

            ReviewComment reviewComment = result.get();

            reviewCommentRepository.delete(reviewComment);

            return BaseRes.builder()
                    .isSuccess(true)
                    .message("댓글 삭제 성공")
                    .result("후기글 댓글 삭제 성공")
                    .build();
    }

    // 대댓글 작성
    @Transactional(readOnly = false)
    public BaseRes createReviewReply(User user, Integer reviewIdx, Integer parentIdx, PostCreateReviewReplyReq postCreateReviewReplyReq) {

        Optional<ReviewComment> parentReviewComment = reviewCommentRepository.findById(parentIdx);

        // 상위댓글이 없을 때
        if (parentReviewComment.equals(0)) {
            throw new ReviewException(ErrorCode.COMMENT_NOT_EXISTS, String.format("parentIdx [ idx : %s ] is not exists.", parentIdx));
        }

        // 대댓글의 내용이 비어있을 때
        if (postCreateReviewReplyReq.getReviewReplyContent() == null || postCreateReviewReplyReq.getReviewReplyContent().isEmpty()) {
            throw new ReviewCommentException(ErrorCode.INVALID_INPUT_VALUE, String.format("Content is empty."));
        }

        ReviewComment childrenReviewComment = ReviewComment.builder()
                .review(Review.builder().idx(reviewIdx).build())
                .user(user)
                .parent(parentReviewComment.get())  // 부모 댓글 설정
                .reviewCommentContent(postCreateReviewReplyReq.getReviewReplyContent())
                .status(true)
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        parentReviewComment.get().getChildren().add(childrenReviewComment);

        reviewCommentRepository.save(childrenReviewComment);

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
