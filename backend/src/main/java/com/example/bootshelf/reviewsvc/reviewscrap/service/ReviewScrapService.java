package com.example.bootshelf.reviewsvc.reviewscrap.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.ReviewException;
import com.example.bootshelf.common.error.entityexception.ReviewScrapException;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import com.example.bootshelf.reviewsvc.review.repository.ReviewRepository;
import com.example.bootshelf.reviewsvc.reviewscrap.model.entity.ReviewScrap;
import com.example.bootshelf.reviewsvc.reviewscrap.model.request.PostCreateReviewScrapReq;
import com.example.bootshelf.reviewsvc.reviewscrap.model.response.GetCheckReviewScrapRes;
import com.example.bootshelf.reviewsvc.reviewscrap.model.response.GetFindReviewScrapRes;
import com.example.bootshelf.reviewsvc.reviewscrap.model.response.PostCreateReviewScrapRes;
import com.example.bootshelf.reviewsvc.reviewscrap.repository.ReviewScrapRepository;
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
public class ReviewScrapService {
    private final ReviewScrapRepository reviewScrapRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public BaseRes createReviewScrap(User user, PostCreateReviewScrapReq req) {
        Review review = reviewRepository.findByIdx(req.getReviewIdx())
                .orElseThrow(() -> new ReviewException(ErrorCode.REVIEW_NOT_EXISTS, String.format("Review  [ %s ] is not exists.", req.getReviewIdx())));

        ReviewScrap reviewScrapResult = reviewScrapRepository.findByUserIdxAndReviewIdx(user.getIdx(), req.getReviewIdx());
        if (reviewScrapResult != null) {
            if (reviewScrapResult.getStatus().equals(true))
                throw new ReviewScrapException(ErrorCode.DUPLICATED_REVIEW_SCRAP, String.format("Review [ %s ] is already scrapped.", req.getReviewIdx()));
            else {
                reviewScrapResult.setStatus(true);
                reviewScrapRepository.save(reviewScrapResult);

                review.increaseScrapCnt();
                reviewRepository.save(review);

                PostCreateReviewScrapRes res = PostCreateReviewScrapRes.toDto(reviewScrapResult);

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("후기글 스크랩 등록 성공")
                        .result(res)
                        .build();
            }
        }

        ReviewScrap reviewScrap = ReviewScrap.toEntity(user, req);

        reviewScrap = reviewScrapRepository.save(reviewScrap);

        review.increaseScrapCnt();
        reviewRepository.save(review);

        PostCreateReviewScrapRes res = PostCreateReviewScrapRes.toDto(reviewScrap);

        return BaseRes.builder()
                .isSuccess(true)
                .message("후기글 스크랩 등록 성공")
                .result(res)
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes findReviewScrapList(User user, Pageable pageable) {
        Page<ReviewScrap> reviewScrapList = reviewScrapRepository.findByUser(user, pageable);
        if (reviewScrapList.isEmpty())
            throw new ReviewScrapException(ErrorCode.REVIEW_SCRAP_IS_EMPTY, "스크랩한 후기가 존재하지 않습니다.");

        List<GetFindReviewScrapRes> resultList = new ArrayList<>();
        for (ReviewScrap reviewScrap : reviewScrapList.getContent()) {
            GetFindReviewScrapRes res = GetFindReviewScrapRes.builder()
                    .reviewScrapIdx(reviewScrap.getIdx())
                    .reviewIdx(reviewScrap.getReview().getIdx())
                    .reviewCategoryIdx(reviewScrap.getReview().getReviewCategory().getIdx())
                    .categoryName(reviewScrap.getReview().getReviewCategory().getCategoryName())
                    .reviewTitle(reviewScrap.getReview().getReviewTitle())
                    .courseName(reviewScrap.getReview().getCourseName())
                    .createdAt(reviewScrap.getCreatedAt())
                    .build();

            resultList.add(res);
        }

        return BaseRes.builder()
                .isSuccess(true)
                .message("리뷰 스크랩 목록 조회 성공")
                .result(resultList)
                .build();
    }

    public BaseRes checkReviewScrap(User user, Integer reviewIdx) {
        ReviewScrap reviewScrapResult = reviewScrapRepository.findByUserIdxAndReviewIdx(user.getIdx(), reviewIdx);
        if (reviewScrapResult != null) {
            if (reviewScrapResult.getStatus().equals(true)) {
                GetCheckReviewScrapRes res = GetCheckReviewScrapRes.builder()
                        .reviewScrapIdx(reviewScrapResult.getIdx())
                        .status(true)
                        .build();

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("후기 스크랩 여부 확인 성공")
                        .result(res)
                        .build();
            }
            else {
                GetCheckReviewScrapRes res = GetCheckReviewScrapRes.builder()
                        .reviewScrapIdx(reviewScrapResult.getIdx())
                        .status(false)
                        .build();

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("후기 스크랩 여부 확인 성공")
                        .result(res)
                        .build();
            }

        } else {
            GetCheckReviewScrapRes res = GetCheckReviewScrapRes.builder()
                    .status(false)
                    .build();

            return BaseRes.builder()
                    .isSuccess(true)
                    .message("후기 스크랩 이력 존재 x")
                    .result(res)
                    .build();
        }
    }

    @Transactional
    public BaseRes deleteReviewScrap(User user, Integer reviewScrapIdx) {
        Optional<ReviewScrap> result = reviewScrapRepository.findByIdx(reviewScrapIdx);
        if (result.isPresent()) {
            ReviewScrap reviewScrap = result.get();
            Review review = reviewScrap.getReview();

            if (reviewScrap.getUser().getIdx().equals(user.getIdx())) {
                reviewScrap.setStatus(false);
                reviewScrapRepository.save(reviewScrap);

                review.decreaseScrapCnt();
                reviewRepository.save(review);

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("리뷰 스크랩 삭제 성공")
                        .build();
            }
            throw new ReviewScrapException(ErrorCode.UNAUTHORIZED_REVIEW_SCRAP,
                    String.format("Current user is  [ %s ] .\n " +
                            "But User who scrapped review is [ %s ].", user.getIdx(), reviewScrap.getUser().getIdx()));
        }
        throw new ReviewScrapException(ErrorCode.REVIEW_SCRAP_NOT_EXISTS, String.format("Review scrap idx [ %s ] is not exists.", reviewScrapIdx));
    }
}
