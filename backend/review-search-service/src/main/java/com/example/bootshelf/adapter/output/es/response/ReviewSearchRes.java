package com.example.bootshelf.adapter.output.es.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewSearchRes {
    private Integer idx;
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
    private String courseName;
    private Integer courseEvaluation;
    private String reviewImage;
    private String profileImage;
}
