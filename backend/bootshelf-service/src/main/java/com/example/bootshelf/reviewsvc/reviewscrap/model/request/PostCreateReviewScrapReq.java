package com.example.bootshelf.reviewsvc.reviewscrap.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateReviewScrapReq {
    private Integer reviewIdx;
}
