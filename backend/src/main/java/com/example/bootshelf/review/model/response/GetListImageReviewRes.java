package com.example.bootshelf.review.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListImageReviewRes {

    private Integer reviewImageIdx;
    private String reviewImage;
}
