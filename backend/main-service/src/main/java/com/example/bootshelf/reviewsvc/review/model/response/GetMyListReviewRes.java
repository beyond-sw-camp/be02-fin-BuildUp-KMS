package com.example.bootshelf.reviewsvc.review.model.response;

import lombok.*;
import org.joda.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMyListReviewRes {

    private Integer idx;
    private Integer reviewCategoryIdx;
    private String title;
    private String content;
    private String reviewImage;
    private String courseName;
    private Integer courseEvaluation;
    private Integer viewCnt;
    private Integer upCnt;
    private Integer scrapCnt;
    private Integer commentCnt;
    private String type;
    private String boardType;
    private LocalDateTime updatedAt;
}
