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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewScrapService {
    private final ReviewScrapRepository reviewScrapRepository;
    private final ReviewRepository reviewRepository;

    public BaseRes createReviewScrap(User user, PostCreateReviewScrapReq req) throws Exception {
        reviewRepository.findByIdx(req.getReviewIdx())
                .orElseThrow(() -> new Exception("해당 리뷰가 존재하지 않습니다." + req.getReviewIdx()));

        ReviewScrap reviewScrapResult = reviewScrapRepository.findByUserIdxAndReviewIdx(user.getIdx(), req.getReviewIdx());
        if (reviewScrapResult != null) {
            if (reviewScrapResult.getStatus().equals(true))
                throw new Exception("이미 스크랩한 리뷰입니다.");
            else {
                reviewScrapResult.setStatus(true);
                reviewScrapRepository.save(reviewScrapResult);
            }
        }

        ReviewScrap reviewScrap = ReviewScrap.toEntity(user, req);

        reviewScrap = reviewScrapRepository.save(reviewScrap);

        PostCreateReviewScrapRes res = PostCreateReviewScrapRes.toDto(reviewScrap);

        return BaseRes.builder()
                .isSuccess(true)
                .message("후기글 스크랩 등록 성공")
                .result(res)
                .build();
    }

    public BaseRes findReviewScrapList(User user, Pageable pageable) throws Exception {
        Page<ReviewScrap> reviewScrapList = reviewScrapRepository.findByUser(user, pageable);
        if (reviewScrapList.isEmpty())
            throw new Exception("리뷰 스크랩한 내역이 존재하지 않습니다.");

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

    public BaseRes deleteReviewScrap(User user, Integer reviewScrapIdx) throws Exception {
        Optional<ReviewScrap> result = reviewScrapRepository.findByIdx(reviewScrapIdx);
        if (result.isPresent()) {
            ReviewScrap reviewScrap = result.get();

            if (reviewScrap.getUser().getIdx().equals(user.getIdx())) {
                reviewScrap.setStatus(false);
                reviewScrapRepository.save(reviewScrap);

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("리뷰 스크랩 삭제 성공")
                        .build();
            }
            throw new Exception("스크랩한 유저와 현재 유저가 일치하지 않습니다.");
        }
        throw new Exception("해당 리뷰가 존재하지 않습니다.");
    }
}
