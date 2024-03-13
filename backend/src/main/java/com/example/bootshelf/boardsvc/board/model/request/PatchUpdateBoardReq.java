package com.example.bootshelf.boardsvc.board.model.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateBoardReq {
    private String boardTitle;

    private String boardContent;

    private Integer boardCategoryIdx;

    private List<String> tagList;
}
