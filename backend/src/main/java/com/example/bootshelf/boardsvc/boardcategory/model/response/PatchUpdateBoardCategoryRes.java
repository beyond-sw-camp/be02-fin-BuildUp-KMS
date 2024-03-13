package com.example.bootshelf.boardsvc.boardcategory.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateBoardCategoryRes {
    private Integer idx;
    private String categoryName;
}
