package com.example.bootshelf.boardimage.model.entity;

import com.example.bootshelf.board.model.entity.Board;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Board_idx")
    private Board board;

    private String filename;

    @Column(nullable = false, length = 200)
    private String boardImage;

    @Column(nullable = false)
    private Boolean status;

}
