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

    private String boardTitle;

    private String boardContent;

    private Integer boardCategoryIdx;

    private List<Integer> boardTagListIdx;

    private Integer viewCnt;

    private Integer upCnt;

    private Integer scrapCnt;

    private Integer commentCnt;

    private String createdAt;

    private String updatedAt;

    private List<String> boardImageList;

    private List<Integer> boardCommentList;

    private String userProfile;

    private String userName;
}
