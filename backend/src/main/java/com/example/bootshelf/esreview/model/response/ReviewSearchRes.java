package com.example.bootshelf.esreview.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewSearchRes {
    private Integer idx;
    private Integer user;
    private String reviewTitle;
    private String reviewContent;
    private Integer reviewCategory;
    private String nickName;
    private String createdAt;
    private Integer viewCnt;
    private Integer commentCnt;
    private Integer upCnt;
    private Integer scrapCnt;
    private String updatedAt;
    private Boolean status;
    private Long totalHits;
    private String courseName;
    private Integer courseEvaluation;
    private String reviewImg;
}
