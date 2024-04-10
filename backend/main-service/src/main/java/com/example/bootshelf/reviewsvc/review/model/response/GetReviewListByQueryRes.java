package com.example.bootshelf.reviewsvc.review.model.response;

import lombok.*;
import org.joda.time.LocalDateTime;

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
    private LocalDateTime createdAt;
    private Integer viewCnt;
    private Integer commentCnt;
    private Integer upCnt;
}
