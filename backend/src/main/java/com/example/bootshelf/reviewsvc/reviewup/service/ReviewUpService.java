package com.example.bootshelf.reviewsvc.reviewup.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.ReviewException;
import com.example.bootshelf.common.error.entityexception.ReviewUpException;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import com.example.bootshelf.reviewsvc.review.repository.ReviewRepository;
import com.example.bootshelf.reviewsvc.reviewup.model.entity.ReviewUp;
import com.example.bootshelf.reviewsvc.reviewup.model.request.PostCreateReviewUpReq;
import com.example.bootshelf.reviewsvc.reviewup.model.response.GetFindReviewUpRes;
import com.example.bootshelf.reviewsvc.reviewup.model.response.PostCreateReviewUpRes;
import com.example.bootshelf.reviewsvc.reviewup.repository.ReviewUpRepository;
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
public class ReviewUpService {
    private final ReviewUpRepository reviewUpRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public BaseRes createReviewUp(User user, PostCreateReviewUpReq req) {
        Review review = reviewRepository.findByIdx(req.getReviewIdx())
                .orElseThrow(() -> new ReviewException(ErrorCode.REVIEW_NOT_EXISTS, String.format("Review [ %s ] is not exists.", req.getReviewIdx())));

        ReviewUp reviewUpResult = reviewUpRepository.findByUserIdxAndReviewIdx(user.getIdx(), req.getReviewIdx());
        if (reviewUpResult != null) {
            if (reviewUpResult.getStatus().equals(true))
                throw new ReviewUpException(ErrorCode.DUPLICATED_REVIEW_UP, String.format("Review [ %s ] is already recommended.", req.getReviewIdx()));
            else {
                reviewUpResult.setStatus(true);
                reviewUpRepository.save(reviewUpResult);

                review.increaseUpCnt();
                reviewRepository.save(review);
            }
        }

        ReviewUp reviewUp = ReviewUp.toEntity(user, req);

        reviewUp = reviewUpRepository.save(reviewUp);

        PostCreateReviewUpRes res = PostCreateReviewUpRes.toDto(reviewUp);

        return BaseRes.builder()
                .isSuccess(true)
                .message("후기 추천 등록 성공")
                .result(res)
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes findReviewUpList(User user, Pageable pageable) {
        Page<ReviewUp> reviewUpList = reviewUpRepository.findByUser(user, pageable);
        if (reviewUpList.isEmpty())
            throw new ReviewUpException(ErrorCode.REVIEW_UP_IS_EMPTY, "추천한 후기가 존재하지 않습니다.");

        List<GetFindReviewUpRes> resultList = new ArrayList<>();
        for (ReviewUp reviewUp : reviewUpList.getContent()) {
            GetFindReviewUpRes res = GetFindReviewUpRes.builder()
                    .reviewUpIdx(reviewUp.getIdx())
                    .reviewIdx(reviewUp.getReview().getIdx())
                    .userIdx(reviewUp.getUser().getIdx())
                    .build();

            resultList.add(res);
        }

        return BaseRes.builder()
                .isSuccess(true)
                .message("게시글 스크랩 목록 조회 성공")
                .result(resultList)
                .build();
    }

    public BaseRes checkReviewUp(User user, Integer reviewIdx) {
        ReviewUp reviewUpResult = reviewUpRepository.findByUserIdxAndReviewIdx(user.getIdx(), reviewIdx);
        if (reviewUpResult != null) {
            return BaseRes.builder()
                    .isSuccess(true)
                    .message("후기 추천 여부 확인 성공")
                    .result(true)
                    .build();
        } else {
            return BaseRes.builder()
                    .isSuccess(true)
                    .message("후기 추천 여부 확인 성공")
                    .result(false)
                    .build();
        }
    }

    @Transactional
    public BaseRes deleteReviewUp(User user, Integer reviewUpIdx) {
        Optional<ReviewUp> result = reviewUpRepository.findByIdx(reviewUpIdx);
        if (result.isPresent()) {
            ReviewUp reviewUp = result.get();
            Review review = reviewUp.getReview();

            if (reviewUp.getUser().getIdx().equals(user.getIdx())) {
                reviewUp.setStatus(false);
                reviewUpRepository.save(reviewUp);

                review.decreaseUpCnt();
                reviewRepository.save(review);

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("게시글 추천 삭제 성공")
                        .build();
            }
            throw new ReviewUpException(ErrorCode.UNAUTHORIZED_REVIEW_UP,
                    String.format("Current user is  [ %s ] .\n " +
                            "But User who recommended review is [ %s ].", user.getIdx(), reviewUp.getUser().getIdx()));
        }
        throw new ReviewUpException(ErrorCode.REVIEW_UP_NOT_EXISTS, String.format("Review recommend idx [ %s ] is not exists.", reviewUpIdx));
    }
}
