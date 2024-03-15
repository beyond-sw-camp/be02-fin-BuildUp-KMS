package com.example.bootshelf.boardsvc.boardcomment.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateBoardCommentRes {
    private Integer idx;
    private String boardCommentContent;
}
