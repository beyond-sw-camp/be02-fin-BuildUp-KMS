package com.example.bootshelf.boardup.model.response;

import com.example.bootshelf.boardscrap.model.entity.BoardScrap;
import com.example.bootshelf.boardup.model.entity.BoardUp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateBoardUpRes {
    private Integer boardUpIdx;
    private Integer userIdx;
    private Integer boardIdx;

    public static PostCreateBoardUpRes toDto(BoardUp boardUp) {
        return PostCreateBoardUpRes.builder()
                .boardUpIdx(boardUp.getIdx())
                .userIdx(boardUp.getUser().getIdx())
                .boardIdx(boardUp.getBoard().getIdx())
                .build();
    }
}