package com.example.bootshelf.domain;

import com.example.bootshelf.adapter.output.es.entity.EsBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Board {

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
