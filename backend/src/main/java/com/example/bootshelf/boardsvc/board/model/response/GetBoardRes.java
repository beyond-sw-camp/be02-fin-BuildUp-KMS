package com.example.bootshelf.boardsvc.board.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetBoardRes {
    private Integer idx;

    private Integer boardCategoryIdx;

    private String boardTitle;

    private String boardContent;

    private String boardCategoryName;

    private List<String> boardTagNameList;

    private Integer viewCnt;

    private Integer upCnt;

    private Integer scrapCnt;

    private Integer commentCnt;

    private String createdAt;

    private String updatedAt;

    private List<String> boardImageList;

    private List<String> tagList;

    private String userProfileImage;

    private String userName;
}
