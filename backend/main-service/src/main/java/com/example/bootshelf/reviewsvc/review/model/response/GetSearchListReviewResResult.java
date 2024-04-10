package com.example.bootshelf.reviewsvc.review.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSearchListReviewResResult {
    private Long totalCnt;
    private Integer totalPages;
    private List<GetSearchListReviewRes> list;
}
