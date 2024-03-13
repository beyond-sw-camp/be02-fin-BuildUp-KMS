package com.example.bootshelf.boardsvc.board.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMyListBoardRes {

    private Integer boardIdx;

    private String boardTitle;

    private String boardContent;

    private Integer boardCategoryIdx;

    private List<Integer> tagList;

    private Integer viewCnt;

    private Integer upCnt;

    private Integer scrapCnt;

    private Integer commentCnt;

    private String updatedAt;
}
