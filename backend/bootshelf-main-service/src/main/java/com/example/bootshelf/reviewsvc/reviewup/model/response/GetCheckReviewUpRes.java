package com.example.bootshelf.reviewsvc.reviewup.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCheckReviewUpRes {
    private Integer reviewUpIdx;
    private Boolean status;
}