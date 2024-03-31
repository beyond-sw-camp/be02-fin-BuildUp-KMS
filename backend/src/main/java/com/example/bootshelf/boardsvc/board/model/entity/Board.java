package com.example.bootshelf.boardsvc.board.model.entity;

import com.example.bootshelf.boardsvc.boardcategory.model.entity.BoardCategory;
import com.example.bootshelf.boardsvc.boardcomment.model.entity.BoardComment;
import com.example.bootshelf.boardsvc.boardhistory.model.entity.BoardHistory;
import com.example.bootshelf.boardsvc.boardimage.model.entity.BoardImage;
import com.example.bootshelf.boardsvc.boardscrap.model.entity.BoardScrap;
import com.example.bootshelf.boardsvc.boardtag.model.entity.BoardTag;
import com.example.bootshelf.boardsvc.boardup.model.entity.BoardUp;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BoardCategory_idx")
    private BoardCategory boardCategory;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardHistory> boardHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardComment> boardCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardImage> boardImageList = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardScrap> boardScrapList = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardUp> boardUpList = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardTag> boardTagList = new ArrayList<>();

    @Column(nullable = false, length = 500)
    private String boardTitle;

    @Column(nullable = false, length = 5000)
    private String boardContent;

    @Column(nullable = false)
    private Integer viewCnt;

    @Column(nullable = false)
    private Integer upCnt;

    @Column(nullable = false)
    private Integer scrapCnt;

    @Column(nullable = false)
    private Integer commentCnt;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

    public void increaseViewCnt() {
        this.viewCnt += 1;
    }

    public void increaseScrapCnt() {
        this.scrapCnt += 1;
    }

    public void decreaseScrapCnt() {
        this.scrapCnt -= 1;
    }

    public void increaseUpCnt() {
        this.upCnt += 1;
    }

    public void decreaseUpCnt() {
        this.upCnt -= 1;
    }

    public void increaseCommentUpCnt() {
        this.commentCnt += 1;
    }

    public void decreaseCommentUpCnt() {
        this.commentCnt -= 1;
    }
}
