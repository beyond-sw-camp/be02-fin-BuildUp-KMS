package com.example.bootshelf.boardsvc.board.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBoardListByQueryRes {
    private Integer boardIdx;
    private String boardTitle;
    private String boardContent;
    private String nickName;
    private String createdAt;
    private Integer viewCnt;
    private Integer commentCnt;
    private Integer upCnt;
}
