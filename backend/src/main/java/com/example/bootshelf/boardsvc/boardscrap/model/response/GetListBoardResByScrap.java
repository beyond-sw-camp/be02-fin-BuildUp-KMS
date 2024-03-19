package com.example.bootshelf.boardsvc.boardscrap.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListBoardResByScrap {

    private Integer idx;

    private String nickName;

    private String userProfileImage;

    private String title;

    private String content;

    private Integer boardCategoryIdx;

    private String boardImg;

    private List<String> tagNameList;

    private Integer viewCnt;

    private Integer upCnt;

    private Integer scrapCnt;

    private Integer commentCnt;

    private String type;  // review, board

    private String boardType;  // write, scrap

    private String createdAt;

    private String updatedAt;
}
