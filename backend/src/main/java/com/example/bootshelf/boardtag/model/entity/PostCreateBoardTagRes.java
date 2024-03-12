package com.example.bootshelf.boardtag.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateBoardTagRes {
    private Integer tagIdx;
    private Integer boardIdx;
}
