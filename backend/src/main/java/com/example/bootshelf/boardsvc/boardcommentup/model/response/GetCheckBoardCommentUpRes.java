package com.example.bootshelf.boardsvc.boardcommentup.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCheckBoardCommentUpRes {
    private Integer boardCommentUpIdx;
    private Boolean status;
}