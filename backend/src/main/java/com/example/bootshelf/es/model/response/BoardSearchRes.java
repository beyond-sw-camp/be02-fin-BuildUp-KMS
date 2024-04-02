package com.example.bootshelf.es.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSearchRes {
    private Integer idx;
    private Integer user;
    private String boardTitle;
    private String boardContent;
    private Integer boardCategory;
    private String nickName;
    private String createdAt;
    private Integer viewCnt;
    private Integer commentCnt;
    private Integer upCnt;
    private Integer scrapCnt;
    private String updatedAt;
    private Boolean status;
    private Long totalHits;
}
