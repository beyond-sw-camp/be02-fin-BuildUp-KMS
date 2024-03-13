package com.example.bootshelf.reviewsvc.review.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateReviewRes {

    private Integer reviewIdx;
    private Integer reviewCategoryIdx;
    private String reviewTitle;
    private String courseName;
    private String reviewContent;
    private Integer courseEvaluation;

}
