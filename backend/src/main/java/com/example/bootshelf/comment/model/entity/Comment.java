package com.example.bootshelf.comment.model.entity;

import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne
    @JoinColumn(name = "Review_idx")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @OneToMany(mappedBy = "comments", fetch = FetchType.LAZY)
    private List<Comment> replyComments;

    @ManyToOne
    @JoinColumn(name = "ref_reviewCommentIdx")
    private Comment comments;

    @Column(nullable = false)
    private String reviewCommentContent;

    @Column(nullable = false)
    private Integer upCnt;

    @Column(nullable = false)
    private Boolean status;

    @Column(updatable = false, nullable = false)
    private String createdAt;

    private String updatedAt;

}

