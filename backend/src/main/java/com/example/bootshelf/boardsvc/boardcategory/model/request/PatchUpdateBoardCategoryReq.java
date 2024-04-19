package com.example.bootshelf.boardsvc.boardcategory.model.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateBoardCategoryReq {

    @NotNull
    private String categoryName;
}
