package com.example.bootshelf.boardsvc.boardcommentup.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateBoardCommentUpReq {
    private Integer boardCommentIdx;
}
