package com.example.bootshelf.reviewsvc.reviewscrap.model.response;

import lombok.Builder;
import lombok.Data;
import org.joda.time.LocalDateTime;

@Data
@Builder
public class GetFindReviewScrapRes {
    private Integer reviewScrapIdx;
    private Integer reviewIdx;
    private Integer reviewCategoryIdx;
    private String categoryName;
    private String reviewTitle;
    private String courseName;
    private LocalDateTime createdAt;
}