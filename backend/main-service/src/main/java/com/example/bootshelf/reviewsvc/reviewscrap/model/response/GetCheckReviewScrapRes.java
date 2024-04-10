package com.example.bootshelf.reviewsvc.reviewscrap.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCheckReviewScrapRes {
    private Integer reviewScrapIdx;
    private Boolean status;
}