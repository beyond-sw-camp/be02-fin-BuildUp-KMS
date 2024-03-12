package com.example.bootshelf.boardcomment.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateBoardReplyRes {
    private Integer parentIdx;
    private String boardCommentContent;
}
