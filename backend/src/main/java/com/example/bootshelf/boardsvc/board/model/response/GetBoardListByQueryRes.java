package com.example.bootshelf.boardsvc.board.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBoardListByQueryRes {
    private Integer boardIdx;
    private String boardTitle;
    private String boardContent;
    private String boardCategoryName;
    private String nickName;
    private String createdAt;
    private Integer viewCnt;
    private Integer commentCnt;
    private Integer upCnt;
    private String boardImg;
    private String userProfileImage;
    private List<String> tagNameList;
    private String updatedAt;
}
