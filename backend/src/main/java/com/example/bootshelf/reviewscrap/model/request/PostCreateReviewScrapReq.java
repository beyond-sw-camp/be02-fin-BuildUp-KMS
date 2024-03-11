package com.example.bootshelf.reviewscrap.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateReviewScrapReq {
    private Integer reviewIdx;
}
