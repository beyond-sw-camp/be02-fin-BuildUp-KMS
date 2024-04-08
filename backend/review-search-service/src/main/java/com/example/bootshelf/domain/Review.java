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
public class Review {

    private Integer idx;
    private String reviewTitle;
    private String reviewContent;
    private Integer reviewCategory;
    private String createdAt;
    private Integer viewCnt;
    private Integer commentCnt;
    private Integer upCnt;
    private Integer scrapCnt;
    private String updatedAt;
    private String courseName;
    private Integer courseEvaluation;
    private String reviewImg;
    private String nickName;
    private String profileImage;

}
