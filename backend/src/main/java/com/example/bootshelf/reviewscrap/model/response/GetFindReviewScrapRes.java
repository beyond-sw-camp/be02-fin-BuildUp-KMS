package com.example.bootshelf.reviewscrap.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetFindReviewScrapRes {
    private Integer reviewScrapIdx;
    private Integer reviewIdx;
    private Integer reviewCategoryIdx;
    private String categoryName;
    private String reviewTitle;
    private String courseName;
    private String createdAt;
}