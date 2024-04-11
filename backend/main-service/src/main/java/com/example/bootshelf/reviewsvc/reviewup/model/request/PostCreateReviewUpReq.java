package com.example.bootshelf.reviewsvc.reviewup.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateReviewUpReq {

    @NotNull
    @Positive
    private Integer reviewIdx;
}
