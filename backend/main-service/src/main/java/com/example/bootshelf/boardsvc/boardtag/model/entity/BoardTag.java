package com.example.bootshelf.boardsvc.boardtag.model.entity;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.tag.model.entity.Tag;
import lombok.*;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

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
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
