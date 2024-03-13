package com.example.bootshelf.boardsvc.boardcommentup.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetFindBoardCommentUpRes {
    private Integer boardCommentUpIdx;
    private Integer userIdx;
    private Integer boardCommentIdx;
}