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
    private Integer user;
    private String reviewTitle;
    private String reviewContent;
    private Integer reviewCategory;
    private String nickName;
    private String createdAt;
    private Integer viewCnt;
    private Integer commentCnt;
    private Integer upCnt;
    private Integer scrapCnt;
    private String updatedAt;
    private Boolean status;
    private Long totalHits;
    private String courseName;
    private Integer courseEvaluation;
    private String reviewImg;

}
