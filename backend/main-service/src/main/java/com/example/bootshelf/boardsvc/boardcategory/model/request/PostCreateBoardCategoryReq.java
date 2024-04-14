package com.example.bootshelf.boardsvc.boardcategory.model.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateBoardCategoryReq {

    @NotNull
    private String categoryName;

}
