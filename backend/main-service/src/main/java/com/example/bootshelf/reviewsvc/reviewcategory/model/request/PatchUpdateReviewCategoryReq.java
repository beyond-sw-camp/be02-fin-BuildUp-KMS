package com.example.bootshelf.reviewsvc.reviewcategory.model.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateReviewCategoryReq {

    @NotNull
    private String categoryName;
}
