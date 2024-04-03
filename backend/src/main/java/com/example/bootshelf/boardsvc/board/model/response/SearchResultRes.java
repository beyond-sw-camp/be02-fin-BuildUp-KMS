package com.example.bootshelf.boardsvc.board.model.response;

import lombok.*;
import org.joda.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResultRes {
    private Integer idx;
    private String title;
    private String content;
    private String categoryName;
    private String nickName;
    private LocalDateTime createdAt;
    private Integer viewCnt;
    private Integer commentCnt;
    private Integer upCnt;
    private String type;
}
