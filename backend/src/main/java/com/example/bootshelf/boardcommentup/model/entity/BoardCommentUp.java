package com.example.bootshelf.boardcommentup.model.entity;

import com.example.bootshelf.board.model.entity.Board;
import com.example.bootshelf.boardcomment.model.entity.BoardComment;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

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

}
