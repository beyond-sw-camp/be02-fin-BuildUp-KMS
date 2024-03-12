package com.example.bootshelf.boardscrap.model.response;

import com.example.bootshelf.boardscrap.model.entity.BoardScrap;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateBoardScrapRes {
    private Integer boardScrapIdx;
    private Integer userIdx;
    private Integer boardIdx;

    private String createdAt;
    private String updatedAt;

    public static PostCreateBoardScrapRes toDto(BoardScrap boardScrap) {
        return PostCreateBoardScrapRes.builder()
                .boardScrapIdx(boardScrap.getIdx())
                .userIdx(boardScrap.getUser().getIdx())
                .boardIdx(boardScrap.getBoard().getIdx())
                .createdAt(boardScrap.getCreatedAt())
                .updatedAt(boardScrap.getUpdatedAt())
                .build();
    }
}