package com.example.bootshelf.reviewcomment.model;

import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.reviewcommentup.model.ReviewCommentUp;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    // 자기 참조를 위한 부모 댓글
    @ManyToOne
    @JoinColumn(name = "parent_idx") // 부모 댓글의 외래키
    private ReviewComment parent;

    // 자식 댓글 목록
    @OneToMany(mappedBy = "parent")
    private List<ReviewComment> children = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "User_idx")
    private User userIdx;

    @ManyToOne
    @JoinColumn(name = "Review_idx")
    private Review reviewIdx;

    @OneToMany(mappedBy = "reviewCommentIdx")
    private List<ReviewCommentUp> reviewCommentUpList = new ArrayList<>();


    private String reviewCommentContent;

    private Integer upCnt;

    private Boolean status;

    @Column(updatable = false, nullable = false)
    private Date createdAt;

    private Date updatedAt;

    @PrePersist
    void createdAt() {
        this.createdAt = Timestamp.from(Instant.now());
        this.updatedAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }
}
