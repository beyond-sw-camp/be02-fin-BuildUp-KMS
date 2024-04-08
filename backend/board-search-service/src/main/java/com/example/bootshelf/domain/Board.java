package com.example.bootshelf.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Board {

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
    private String boardImg;

}
