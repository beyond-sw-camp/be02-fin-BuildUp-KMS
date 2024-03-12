package com.example.bootshelf.boardup.model.entity;

import com.example.bootshelf.board.model.entity.Board;
import com.example.bootshelf.boardup.model.request.PostCreateBoardUpReq;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Board_idx")
    private Board board;

    @Column(nullable = false)
    private Boolean status;

    public static BoardUp toEntity(User user, PostCreateBoardUpReq req) {
        return BoardUp.builder()
                .user(User.builder().idx(user.getIdx()).build())
                .board(Board.builder().idx(req.getBoardIdx()).build())
                .status(true)
                .build();
    }
}
