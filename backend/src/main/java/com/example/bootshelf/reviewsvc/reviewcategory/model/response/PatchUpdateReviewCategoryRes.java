package com.example.bootshelf.reviewsvc.reviewcategory.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateReviewCategoryRes {
    private Integer idx;
    private String categoryName;
}
