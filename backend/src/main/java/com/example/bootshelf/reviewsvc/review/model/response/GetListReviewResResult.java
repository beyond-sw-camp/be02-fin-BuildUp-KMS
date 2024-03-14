package com.example.bootshelf.reviewsvc.review.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListReviewResResult {
    private Long totalCnt;
    private Integer totalPages;
    private List<GetListReviewRes> list;
}
