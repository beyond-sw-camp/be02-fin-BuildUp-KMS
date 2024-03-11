package com.example.bootshelf.reviewscrap.model.response;

import com.example.bootshelf.reviewscrap.model.ReviewScrap;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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