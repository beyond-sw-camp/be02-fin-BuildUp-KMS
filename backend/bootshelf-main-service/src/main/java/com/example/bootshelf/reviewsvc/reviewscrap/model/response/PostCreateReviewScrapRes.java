package com.example.bootshelf.reviewsvc.reviewscrap.model.response;

import com.example.bootshelf.reviewsvc.reviewscrap.model.entity.ReviewScrap;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateReviewScrapRes {
    private Integer reviewScrapIdx;
    private Integer userIdx;
    private Integer reviewIdx;

    private String createdAt;
    private String updatedAt;

    public static PostCreateReviewScrapRes toDto(ReviewScrap reviewScrap) {
        return PostCreateReviewScrapRes.builder()
                .reviewScrapIdx(reviewScrap.getIdx())
                .userIdx(reviewScrap.getUser().getIdx())
                .reviewIdx(reviewScrap.getReview().getIdx())
                .createdAt(reviewScrap.getCreatedAt())
                .updatedAt(reviewScrap.getUpdatedAt())
                .build();
    }
}