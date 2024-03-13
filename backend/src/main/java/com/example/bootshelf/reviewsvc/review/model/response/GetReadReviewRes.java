package com.example.bootshelf.reviewsvc.review.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetReadReviewRes {

    private Integer reviewIdx;
    private Integer userIdx;
    private String userNickName;
    private String reviewTitle;
    private String reviewContent;
    private String courseName;
    private Integer courseEvaluation;
    private Integer viewCnt;
    private Integer upCnt;
    private Integer commentCnt;
    private String updatedAt;
    private List<GetListImageReviewRes> reviewImageList;
    private List<GetListCommentReviewRes> reviewCommentList;

}
