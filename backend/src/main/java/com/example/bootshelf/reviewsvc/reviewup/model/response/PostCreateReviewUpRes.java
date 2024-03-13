package com.example.bootshelf.reviewsvc.reviewup.model.response;

import com.example.bootshelf.reviewsvc.reviewup.model.entity.ReviewUp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateReviewUpRes {
    private Integer reviewUpIdx;
    private Integer userIdx;
    private Integer reviewIdx;

    public static PostCreateReviewUpRes toDto(ReviewUp reviewUp) {
        return PostCreateReviewUpRes.builder()
                .reviewUpIdx(reviewUp.getIdx())
                .userIdx(reviewUp.getUser().getIdx())
                .reviewIdx(reviewUp.getReview().getIdx())
                .build();
    }
}