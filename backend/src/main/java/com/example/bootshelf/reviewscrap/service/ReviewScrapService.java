package com.example.bootshelf.reviewscrap.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.review.repository.ReviewRepository;
import com.example.bootshelf.reviewscrap.model.ReviewScrap;
import com.example.bootshelf.reviewscrap.model.request.PostCreateReviewScrapReq;
import com.example.bootshelf.reviewscrap.model.response.GetFindReviewScrapRes;
import com.example.bootshelf.reviewscrap.model.response.PostCreateReviewScrapRes;
import com.example.bootshelf.reviewscrap.repository.ReviewScrapRepository;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewScrapService {
    private final ReviewScrapRepository reviewScrapRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public BaseRes createReviewScrap(User user, PostCreateReviewScrapReq req) {
//        Review review = reviewRepository.findByIdx(req.getReviewIdx())
//                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.REVIEW_NOT_EXISTS, "해당 리뷰가 존재하지 않습니다." + req.getReviewIdx()));

        ReviewScrap reviewScrap = ReviewScrap.toEntity(user, req);

        reviewScrap = reviewScrapRepository.save(reviewScrap);

        PostCreateReviewScrapRes res = PostCreateReviewScrapRes.toDto(reviewScrap);

        return BaseRes.builder()
                .isSuccess(true)
                .message("후기글 스크랩 등록 성공")
                .result(res)
                .build();
    }

    public BaseRes findReviewScrapList(User user) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ReviewScrap> reviewScrapList = reviewScrapRepository.findByUserIdx(user, pageable);

        List<GetFindReviewScrapRes> resultList = reviewScrapList.getContent().stream().map(reviewScrap -> GetFindReviewScrapRes.builder()
                .reviewScrapIdx(reviewScrap.getIdx())
                .reviewIdx(reviewScrap.getReview().getIdx())
                .reviewCategoryIdx(reviewScrap.getReview().getReviewCategory() != null ? reviewScrap.getReview().getReviewCategory().getIdx() : null)
                .categoryName(reviewScrap.getReview().getReviewCategory() != null ? reviewScrap.getReview().getReviewCategory().getCategoryName() : null)
                .reviewTitle(reviewScrap.getReview().getReviewTitle())
                .courseName(reviewScrap.getReview().getCourseName())
                .createdAt(reviewScrap.getCreatedAt())
                .build()).collect(Collectors.toList());

        return BaseRes.builder()
                .isSuccess(true)
                .message("리뷰 스크랩 목록 조회 성공")
                .result(resultList)
                .build();
    }

    public BaseRes deleteReviewScrap(User user, Integer reviewScrapIdx) throws Exception {
        Optional<ReviewScrap> result = reviewScrapRepository.findByIdx(reviewScrapIdx);
        if (result.isPresent()) {
            ReviewScrap reviewScrap = result.get();
            reviewScrap.setStatus(false);
            reviewScrapRepository.save(reviewScrap);

            return BaseRes.builder()
                    .isSuccess(true)
                    .message("리뷰 스크랩 삭제 성공")
                    .build();
        }
        throw new Exception("해당 리뷰가 존재하지 않습니다.");
    }
}
