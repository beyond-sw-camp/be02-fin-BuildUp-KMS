package com.example.bootshelf.boardsvc.boardscrap.model.response;

import com.example.bootshelf.boardsvc.boardscrap.model.entity.BoardScrap;
import lombok.Builder;
import lombok.Data;
import org.joda.time.LocalDateTime;

@Data
@Builder
public class PostCreateBoardScrapRes {
    private Integer boardScrapIdx;
    private Integer userIdx;
    private Integer boardIdx;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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