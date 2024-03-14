package com.example.bootshelf.boardsvc.boardcommentup.model.entity;

import com.example.bootshelf.boardsvc.boardcomment.model.entity.BoardComment;
import com.example.bootshelf.boardsvc.boardcommentup.model.request.PostCreateBoardCommentUpReq;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BoardComment_idx")
    private BoardComment boardComment;

    private Boolean status;

    public static BoardCommentUp toEntity(User user, PostCreateBoardCommentUpReq req) {
        return BoardCommentUp.builder()
                .user(User.builder().idx(user.getIdx()).build())
                .boardComment(BoardComment.builder().idx(req.getBoardCommentIdx()).build())
                .status(true)
                .build();
    }
}
