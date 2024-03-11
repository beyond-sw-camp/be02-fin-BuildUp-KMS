package com.example.bootshelf.reviewscrap.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.exception.EntityNotFoundException;
import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.review.repository.ReviewRepository;
import com.example.bootshelf.reviewscrap.model.ReviewScrap;
import com.example.bootshelf.reviewscrap.model.request.PostCreateReviewScrapReq;
import com.example.bootshelf.reviewscrap.model.response.PostCreateReviewScrapRes;
import com.example.bootshelf.reviewscrap.repository.ReviewScrapRepository;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewScrapService {
    private final ReviewScrapRepository reviewScrapRepository;
    private final ReviewRepository reviewRepository;

    public BaseRes createReviewScrap(User user, PostCreateReviewScrapReq req) {
        Review review = reviewRepository.findById(req.getReviewIdx())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.REVIEW_NOT_EXISTS, "해당 리뷰가 존재하지 않습니다." + req.getReviewIdx()));

        ReviewScrap reviewScrap = ReviewScrap.toEntity(user, req);

        reviewScrap = reviewScrapRepository.save(reviewScrap);

        PostCreateReviewScrapRes res = PostCreateReviewScrapRes.toDto(reviewScrap);

        return BaseRes.builder()
                .isSuccess(true)
                .message("후기글 스크랩 등록 성공")
                .result(res)
                .build();
    }
}
