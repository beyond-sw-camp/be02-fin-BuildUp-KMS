package com.example.bootshelf.reviewsvc.reviewcategory.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateReviewCategoryReq {

    private String categoryName;
}
