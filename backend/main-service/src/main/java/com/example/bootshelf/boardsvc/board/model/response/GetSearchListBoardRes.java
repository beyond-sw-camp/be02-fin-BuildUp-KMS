package com.example.bootshelf.boardsvc.board.model.response;

import lombok.*;
import org.joda.time.LocalDateTime;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSearchListBoardRes {

    private Integer idx;

    private String nickName;

    private String profileImage;

    private String boardtitle;

    private String boardcontent;

    private Integer boardCategoryIdx;

    private String boardImg;

    private List<GetTagNameRes> tags;

    private Integer viewCnt;

    private Integer upCnt;

    private Integer scrapCnt;

    private Integer commentCnt;

    private String type;

    private String boardType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
