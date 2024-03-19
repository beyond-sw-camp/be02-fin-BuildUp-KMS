package com.example.bootshelf.reviewsvc.reviewscrap.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetScrapListReviewRes {

    private Integer idx;
    private Integer userIdx;
    private String nickName;
    private String title;
    private String content;
    private String courseName;
    private Integer courseEvaluation;
    private Integer viewCnt;
    private Integer upCnt;
    private Integer scrapCnt;
    private Integer commentCnt;
    private String updatedAt;

}
