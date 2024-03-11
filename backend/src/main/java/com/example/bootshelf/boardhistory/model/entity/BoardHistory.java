package com.example.bootshelf.boardhistory.model.entity;

import com.example.bootshelf.board.model.entity.Board;
import com.example.bootshelf.boardcommentup.model.entity.BoardCommentUp;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardHistory {

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
    private Date deletedAt;

    @PrePersist
    void deletedAt() {
        this.deletedAt = Timestamp.from(Instant.now());
    }

}
