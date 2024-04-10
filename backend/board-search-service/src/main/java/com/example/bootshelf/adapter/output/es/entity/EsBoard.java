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
    private String boardtitle;

    @Field(type = FieldType.Text, analyzer = "nori")
    private String boardcontent;

    @Field(type = FieldType.Integer)
    private Integer viewcnt;

    @Field(type = FieldType.Integer)
    private Integer upcnt;

    @Field(type = FieldType.Integer)
    private Integer scrapcnt;

    @Field(type = FieldType.Integer)
    private Integer commentcnt;

    @Field(type = FieldType.Text)
    private String nickname;

    @Field(type = FieldType.Text)
    private String profileimage;

    @Field(type = FieldType.Text)
    private String boardImage;

    private String createdAt;

    private String updatedat;

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