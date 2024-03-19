package com.example.bootshelf.reviewsvc.reviewscrap.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetScrapListReviewResResult {
    private Long totalCnt;
    private Integer totalPages;
    private List<GetScrapListReviewRes> list;
}
