package com.example.bootshelf.boardcomment.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateBoardCommentRes {
    private String boardCommentContent;
}
