package com.example.bootshelf.boardsvc.board.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetReadBoardRes {
    private Integer idx;

    private Integer boardCategoryIdx;

    private String boardTitle;

    private String boardContent;

    private String boardCategoryName;

    private Integer viewCnt;

    private Integer upCnt;

    private Integer scrapCnt;

    private Integer commentCnt;

    private String createdAt;

    private String updatedAt;

    private List<String> boardImageList;

    private List<GetListCommentBoardRes> boardCommentList;

    private List<String> tagList;

    private String userProfileImage;

    private String nickName;
}
