package com.example.bootshelf.boardsvc.board.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListBoardRes {

    private Integer boardIdx;

    private String userNickName;

    private String userProfileImage;

    private String boardTitle;

    private String boardContent;

    private Integer boardCategoryIdx;

    private String boardImg;

    private List<String> tagNameList;

    private Integer viewCnt;

    private Integer upCnt;

    private Integer scrapCnt;

    private Integer commentCnt;

    private String updatedAt;
}
