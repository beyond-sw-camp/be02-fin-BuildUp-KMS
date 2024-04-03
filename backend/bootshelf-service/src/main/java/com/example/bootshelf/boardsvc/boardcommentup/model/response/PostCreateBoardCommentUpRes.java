package com.example.bootshelf.boardsvc.boardcommentup.model.response;

import com.example.bootshelf.boardsvc.boardcommentup.model.entity.BoardCommentUp;
import com.example.bootshelf.boardsvc.boardup.model.entity.BoardUp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateBoardCommentUpRes {
    private Integer boardCommentUpIdx;
    private Integer userIdx;
    private Integer boardCommentIdx;

    public static PostCreateBoardCommentUpRes toDto(BoardCommentUp boardCommentUp) {
        return PostCreateBoardCommentUpRes.builder()
                .boardCommentUpIdx(boardCommentUp.getIdx())
                .userIdx(boardCommentUp.getUser().getIdx())
                .boardCommentIdx(boardCommentUp.getBoardComment().getIdx())
                .build();
    }
}