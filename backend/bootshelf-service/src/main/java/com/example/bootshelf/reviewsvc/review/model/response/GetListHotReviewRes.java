package com.example.bootshelf.reviewsvc.review.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListHotReviewRes {
    private Integer idx;
    private Integer userIdx;
    private String nickName;
    private String profileImage;
    private String title;
    private String content;
    private String image;
    private String courseName;
    private Integer courseEvaluation;
    private Integer viewCnt;
    private Integer upCnt;
    private Integer scrapCnt;
    private Integer commentCnt;
    private String createdAt;
    private String updatedAt;
    private List<String> tagNameList;
    private String type;
}
