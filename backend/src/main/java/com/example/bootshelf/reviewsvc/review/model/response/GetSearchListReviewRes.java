package com.example.bootshelf.reviewsvc.review.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSearchListReviewRes {

    private Integer reviewIdx;
    private Integer userIdx;
    private String userNickName;
    private Integer reviewCategoryIdx;
    private String reviewCategoryName;
    private String reviewTitle;
    private String courseName;
    private Integer viewCnt;
    private Integer upCnt;
    private Integer scrapCnt;
    private Integer commentCnt;
    private String updatedAt;
}
