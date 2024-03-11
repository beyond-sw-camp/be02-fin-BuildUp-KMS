package com.example.bootshelf.reviewscrap.model.response;

import com.example.bootshelf.reviewscrap.model.ReviewScrap;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateReviewScrapRes {
    private Integer reviewScrapIdx;
    private Integer userIdx;
    private Integer reviewIdx;

    public static PostCreateReviewScrapRes toDto(ReviewScrap reviewScrap) {
        return PostCreateReviewScrapRes.builder()
                .reviewScrapIdx(reviewScrap.getIdx())
                .userIdx(reviewScrap.getUser().getIdx())
                .reviewIdx(reviewScrap.getReview().getIdx())
                .build();
    }
}