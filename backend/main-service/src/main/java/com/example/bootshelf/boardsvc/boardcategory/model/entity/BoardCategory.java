package com.example.bootshelf.boardsvc.boardcategory.model.entity;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import lombok.*;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @OneToMany(mappedBy = "boardCategory", fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();

    @Column(nullable = false, length = 45)
    private String categoryName;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
