package com.example.bootshelf.esboard.model.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Document(indexName = "board")
public class EsBoard {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Integer)
    private Integer user;

    @Field(type = FieldType.Integer)
    private Integer boardCategory;

    @Field(type = FieldType.Text, analyzer = "nori")
    private String boardTitle;

    @Field(type = FieldType.Text, analyzer = "nori")
    private String boardContent;

    @Field(type = FieldType.Integer)
    private Integer viewCnt;

    @Field(type = FieldType.Integer)
    private Integer upCnt;

    @Field(type = FieldType.Integer)
    private Integer scrapCnt;

    @Field(type = FieldType.Integer)
    private Integer commentCnt;

    @Field(type = FieldType.Boolean)
    private Boolean status;

//    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd")
    private String createdAt;

//    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd")
    private String updatedAt;
}