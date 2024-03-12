package com.example.bootshelf.reviewcomment.model.entity;

import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.reviewcommentup.model.ReviewCommentUp;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_idx") // 부모 댓글의 외래키
    private ReviewComment parent;

    // 자식 댓글 목록
    @OneToMany(mappedBy = "parent")
    private List<ReviewComment> children = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Review_idx")
    private Review review;

    @OneToMany(mappedBy = "reviewComment")
    private List<ReviewCommentUp> reviewCommentUpList = new ArrayList<>();


    private String reviewCommentContent;

    @ColumnDefault("0")
    private Integer upCnt;

    private Boolean status;

    @Column(updatable = false, nullable = false)
    private String createdAt;

    private String updatedAt;
}
