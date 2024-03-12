package com.example.bootshelf.boardcomment.model.entity;

import com.example.bootshelf.board.model.entity.Board;
import com.example.bootshelf.boardcommentup.model.entity.BoardCommentUp;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    // 자기 참조를 위한 부모 댓글
    @ManyToOne
    @JoinColumn(name = "parent_idx") // 부모 댓글의 외래키
    private BoardComment parent;

    // 자식 댓글 목록
    @OneToMany(mappedBy = "parent")
    private List<BoardComment> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Board_idx")
    private Board board;

    @OneToMany(mappedBy = "boardComment", fetch = FetchType.LAZY)
    private List<BoardCommentUp> boardCommentUpList = new ArrayList<>();

    @Column(nullable = false, length = 100)
    private String commentContent;

    @Column(nullable = false)
    private Integer upCnt;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

}
