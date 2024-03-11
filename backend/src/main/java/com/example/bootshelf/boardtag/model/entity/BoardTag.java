package com.example.bootshelf.boardtag.model.entity;

import com.example.bootshelf.board.model.entity.Board;
import com.example.bootshelf.tag.model.entity.Tag;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tag_idx")
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Board_idx")
    private Board board;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

}
