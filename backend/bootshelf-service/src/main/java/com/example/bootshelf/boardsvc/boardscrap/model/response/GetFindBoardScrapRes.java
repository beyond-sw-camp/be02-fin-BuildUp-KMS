package com.example.bootshelf.boardsvc.boardscrap.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetFindBoardScrapRes {
    private Integer boardScrapIdx;
    private Integer boardIdx;
    private Integer boardCategoryIdx;
    private String createdAt;
    private String updatedAt;
}