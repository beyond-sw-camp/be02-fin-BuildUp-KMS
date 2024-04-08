package com.example.bootshelf.esboard.model.response;

import com.example.bootshelf.esboard.model.entity.EsBoard;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSearchRes {
    private Integer idx;
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
    private String profileImage;
    private String boardImage;
    private List<EsBoard.EsTag> tags;
}
