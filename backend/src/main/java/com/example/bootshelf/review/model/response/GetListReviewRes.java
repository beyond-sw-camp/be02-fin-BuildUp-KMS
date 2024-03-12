package com.example.bootshelf.review.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListReviewRes {

    private Integer reviewIdx;
    private Integer userIdx;
    private String userNickName;
    private String reviewTitle;
    private String reviewContent;
    private String reviewImage;
    private String courseName;
    private Integer courseEvaluation;
    private Integer viewCnt;
    private Integer upCnt;
    private Integer scrapCnt;
    private Integer commentCnt;
    private String updatedAt;

}
