package com.example.bootshelf.es.model;

import com.example.bootshelf.boardsvc.boardcategory.model.entity.BoardCategory;
import com.example.bootshelf.boardsvc.boardcomment.model.entity.BoardComment;
import com.example.bootshelf.boardsvc.boardhistory.model.entity.BoardHistory;
import com.example.bootshelf.boardsvc.boardimage.model.entity.BoardImage;
import com.example.bootshelf.boardsvc.boardscrap.model.entity.BoardScrap;
import com.example.bootshelf.boardsvc.boardtag.model.entity.BoardTag;
import com.example.bootshelf.boardsvc.boardup.model.entity.BoardUp;
import com.example.bootshelf.user.model.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "board")
@Mapping(mappingPath = "elastic/performance-mapping.json")
@Setting(settingPath = "elastic/performance-setting.json")
public class BoardDocument {
    @Id
    @Field(type = FieldType.Keyword)
    private Integer idx;

    @Field(type = FieldType.Text)
    private User user;

    @Field(type = FieldType.Text)
    private BoardCategory boardCategory;

    @Field(type = FieldType.Text)
    private String boardTitle;

    @Field(type = FieldType.Text)
    private String boardContent;

    @Field(type = FieldType.Text)
    private Integer viewCnt;

    @Field(type = FieldType.Text)
    private Integer upCnt;

    @Field(type = FieldType.Text)
    private Integer scrapCnt;

    @Field(type = FieldType.Text)
    private Integer commentCnt;

    @Field(type = FieldType.Text)
    private Boolean status;

    @Field(type = FieldType.Date, format = {DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis})
    private LocalDateTime createdAt;

    @Field(type = FieldType.Date, format = {DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis})
    private LocalDateTime updatedAt;

    @Builder
    public BoardDocument(Integer idx, User user, BoardCategory boardCategory, String boardTitle, String boardContent, Integer viewCnt, Integer upCnt, Integer scrapCnt, Integer commentCnt, Boolean status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idx = idx;
        this.user = user;
        this.boardCategory = boardCategory;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.viewCnt = viewCnt;
        this.upCnt = upCnt;
        this.scrapCnt = scrapCnt;
        this.commentCnt = commentCnt;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
