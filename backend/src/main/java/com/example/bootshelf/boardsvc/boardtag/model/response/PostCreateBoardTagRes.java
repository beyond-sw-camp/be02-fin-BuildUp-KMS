package com.example.bootshelf.boardsvc.boardtag.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateBoardTagRes {
    private Integer tagIdx;
    private Integer boardIdx;
}
