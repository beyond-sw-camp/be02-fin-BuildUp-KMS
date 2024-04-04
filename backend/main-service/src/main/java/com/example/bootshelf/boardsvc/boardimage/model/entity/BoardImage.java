package com.example.bootshelf.boardsvc.boardimage.model.entity;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
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

    @Column(nullable = false, length = 500)
    private String boardImage;

    @Column(nullable = false)
    private Boolean status;

}
