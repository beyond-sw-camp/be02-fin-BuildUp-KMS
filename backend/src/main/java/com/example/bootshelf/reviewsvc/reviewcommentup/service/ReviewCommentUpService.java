package com.example.bootshelf.reviewsvc.reviewcommentup.service;

import com.example.bootshelf.boardsvc.boardcommentup.model.response.GetCheckBoardCommentUpRes;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.*;
import com.example.bootshelf.reviewsvc.reviewcomment.model.entity.ReviewComment;
import com.example.bootshelf.reviewsvc.reviewcomment.repository.ReviewCommentRepository;
import com.example.bootshelf.reviewsvc.reviewcommentup.model.entity.ReviewCommentUp;
import com.example.bootshelf.reviewsvc.reviewcommentup.model.request.PostCreateReviewCommentUpReq;
import com.example.bootshelf.reviewsvc.reviewcommentup.model.response.GetCheckReviewCommentUpRes;
import com.example.bootshelf.reviewsvc.reviewcommentup.model.response.GetFindReviewCommentUpRes;
import com.example.bootshelf.reviewsvc.reviewcommentup.model.response.PostCreateReviewCommentUpRes;
import com.example.bootshelf.reviewsvc.reviewcommentup.repository.ReviewCommentUpRepository;
import com.example.bootshelf.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewCommentUpService {
    private final ReviewCommentUpRepository reviewCommentUpRepository;
    private final ReviewCommentRepository reviewCommentRepository;

    @Transactional
    public BaseRes createReviewCommentUp(User user, PostCreateReviewCommentUpReq req) {
        ReviewComment reviewComment = reviewCommentRepository.findByIdx(req.getReviewCommentIdx())
                .orElseThrow(() -> new ReviewCommentException(ErrorCode.REVIEW_COMMENT_NOT_EXISTS, String.format("Review Comment [ %s ] is not exists.", req.getReviewCommentIdx())));

        ReviewCommentUp reviewCommentUpResult = reviewCommentUpRepository.findByUserIdxAndReviewCommentIdx(user.getIdx(), req.getReviewCommentIdx());
        if (reviewCommentUpResult != null) {
            if (reviewCommentUpResult.getStatus().equals(true))
                throw new ReviewCommentUpException(ErrorCode.DUPLICATED_REVIEW_COMMENT_UP, String.format("Review Comment [ %s ] is already recommended.", req.getReviewCommentIdx()));
            else {
                reviewCommentUpResult.setStatus(true);
                reviewCommentUpRepository.save(reviewCommentUpResult);

                reviewComment.increaseUpCnt();
                reviewCommentRepository.save(reviewComment);

                PostCreateReviewCommentUpRes res = PostCreateReviewCommentUpRes.toDto(reviewCommentUpResult);

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("게시판 댓글 추천 수정 성공")
                        .result(res)
                        .build();
            }
        }

        ReviewCommentUp reviewCommentUp = ReviewCommentUp.toEntity(user, req);
        reviewCommentUp = reviewCommentUpRepository.save(reviewCommentUp);

        reviewComment.increaseUpCnt();
        reviewCommentRepository.save(reviewComment);

        PostCreateReviewCommentUpRes res = PostCreateReviewCommentUpRes.toDto(reviewCommentUp);

        return BaseRes.builder()
                .isSuccess(true)
                .message("게시판 댓글 추천 등록 성공")
                .result(res)
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes findReviewCommentUpList(User user, Pageable pageable) {
        Page<ReviewCommentUp> reviewCommentUpList = reviewCommentUpRepository.findByUser(user, pageable);
        if (reviewCommentUpList.isEmpty())
            throw new ReviewCommentUpException(ErrorCode.REVIEW_COMMENT_UP_IS_EMPTY, "추천한 후기 댓글이 존재하지 않습니다.");

        List<GetFindReviewCommentUpRes> resultList = new ArrayList<>();
        for (ReviewCommentUp reviewCommentUp : reviewCommentUpList.getContent()) {
            GetFindReviewCommentUpRes res = GetFindReviewCommentUpRes.builder()
                    .reviewCommentUpIdx(reviewCommentUp.getIdx())
                    .reviewCommentIdx(reviewCommentUp.getReviewComment().getIdx())
                    .userIdx(reviewCommentUp.getUser().getIdx())
                    .build();

            resultList.add(res);
        }

        return BaseRes.builder()
                .isSuccess(true)
                .message("게시판 댓글 추천 목록 조회 성공")
                .result(resultList)
                .build();
    }


    public BaseRes checkReviewCommentUp(User user, Integer reviewCommentIdx) {
        ReviewCommentUp reviewCommentUpResult = reviewCommentUpRepository.findByUserIdxAndReviewCommentIdx(user.getIdx(), reviewCommentIdx);
        if (reviewCommentUpResult != null) {
            if (reviewCommentUpResult.getStatus().equals(true)) {
                GetCheckReviewCommentUpRes res = GetCheckReviewCommentUpRes.builder()
                        .reviewCommentUpIdx(reviewCommentUpResult.getIdx())
                        .status(true)
                        .build();

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("후기 댓글 추천 여부 확인 성공")
                        .result(res)
                        .build();
            } else {
                GetCheckReviewCommentUpRes res = GetCheckReviewCommentUpRes.builder()
                        .reviewCommentUpIdx(reviewCommentUpResult.getIdx())
                        .status(false)
                        .build();

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("후기 댓글 추천 여부 확인 성공")
                        .result(res)
                        .build();
            }

        } else {

            GetCheckReviewCommentUpRes res2 = GetCheckReviewCommentUpRes.builder()
                    .status(false)
                    .build();

            return BaseRes.builder()
                    .isSuccess(true)
                    .message("후기 댓글 추천 이력 존재 x")
                    .result(res2)
                    .build();
        }
    }

    @Transactional
    public BaseRes deleteReviewCommentUp(User user, Integer reviewCommentUpIdx) {
        Optional<ReviewCommentUp> result = reviewCommentUpRepository.findByIdx(reviewCommentUpIdx);
        if (result.isPresent()) {
            ReviewCommentUp reviewCommentUp = result.get();
            ReviewComment reviewComment = reviewCommentUp.getReviewComment();

            if (reviewCommentUp.getUser().getIdx().equals(user.getIdx())) {
                reviewCommentUp.setStatus(false);
                reviewCommentUpRepository.save(reviewCommentUp);

                reviewComment.decreaseUpCnt();
                reviewCommentRepository.save(reviewComment);

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("후기 댓글 추천 삭제 성공")
                        .build();
            }
            throw new ReviewUpException(ErrorCode.UNAUTHORIZED_REVIEW_COMMENT_UP,
                    String.format("Current user is  [ %s ] .\n " +
                            "But User who recommended review comment is [ %s ].", user.getIdx(), reviewCommentUp.getUser().getIdx()));
        }
        throw new ReviewUpException(ErrorCode.REVIEW_UP_NOT_EXISTS, String.format("Review recommend idx [ %s ] is not exists.", reviewCommentUpIdx));
    }
}
