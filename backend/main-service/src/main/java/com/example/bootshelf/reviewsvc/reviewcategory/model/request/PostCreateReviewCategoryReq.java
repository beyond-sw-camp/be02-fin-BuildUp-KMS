package com.example.bootshelf.reviewsvc.reviewcategory.model.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateReviewCategoryReq {

    @NotNull
    private String categoryName;

}
