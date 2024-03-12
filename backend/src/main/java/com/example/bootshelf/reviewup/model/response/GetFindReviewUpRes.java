package com.example.bootshelf.reviewup.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetFindReviewUpRes {
    private Integer reviewUpIdx;
    private Integer userIdx;
    private Integer reviewIdx;
}