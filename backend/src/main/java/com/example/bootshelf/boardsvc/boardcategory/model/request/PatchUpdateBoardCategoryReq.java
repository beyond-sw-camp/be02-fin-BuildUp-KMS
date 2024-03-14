package com.example.bootshelf.boardsvc.boardcategory.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateBoardCategoryReq {

    private String categoryName;
}
