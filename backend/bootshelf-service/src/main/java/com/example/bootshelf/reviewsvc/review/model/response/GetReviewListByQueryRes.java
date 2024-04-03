package com.example.bootshelf.reviewsvc.review.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetReviewListByQueryRes {
    private Integer reviewIdx;
    private String reviewTitle;
    private String reviewContent;
    private String reviewCategoryName;
    private String nickName;
    private String createdAt;
    private Integer viewCnt;
    private Integer commentCnt;
    private Integer upCnt;
}
