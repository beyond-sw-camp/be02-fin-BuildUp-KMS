package com.example.bootshelf.adapter.output.es.entity;
import lombok.*;


import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.List;

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
    private Integer boardcategory_idx;

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

    @Field(type = FieldType.Text)
    private String nickName;

    @Field(type = FieldType.Text)
    private String profileImage;

    @Field(type = FieldType.Text)
    private String boardImage;

    private String createdAt;

    private String updatedAt;

    @Field(type = FieldType.Nested)
    private List<EsTag> tags;

    public static class EsTag {

        @Field(type = FieldType.Text, analyzer = "nori")
        private String tagname;

        public String getTagname() {
            return tagname;
        }

        public void setTagname(String tagname) {
            this.tagname = tagname;
        }
    }
}