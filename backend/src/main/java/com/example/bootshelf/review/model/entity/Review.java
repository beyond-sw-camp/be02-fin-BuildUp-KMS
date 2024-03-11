package com.example.bootshelf.review.model.entity;

import com.example.bootshelf.reviewcategory.model.ReviewCategory;
import com.example.bootshelf.reviewcomment.model.ReviewComment;
import com.example.bootshelf.reviewhistory.model.ReviewHistory;
import com.example.bootshelf.reviewimage.model.ReviewImage;
import com.example.bootshelf.reviewscrap.model.ReviewScrap;
import com.example.bootshelf.reviewup.model.ReviewUp;
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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne
    @JoinColumn(name = "User_idx")
    private User userIdx;

    @ManyToOne
    @JoinColumn(name = "ReviewCategory_idx")
    private ReviewCategory reviewCategoryIdx;

    @OneToMany(mappedBy = "reviewIdx")
    private List<ReviewUp> reviewUpList = new ArrayList<>();

    @OneToMany(mappedBy = "reviewIdx")
    private List<ReviewScrap> reviewScrapList = new ArrayList<>();

    @OneToMany(mappedBy = "reviewIdx")
    private List<ReviewImage> reviewImageList = new ArrayList<>();

    @OneToMany(mappedBy = "reviewIdx")
    private List<ReviewHistory> reviewHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "reviewIdx")
    private List<ReviewComment> reviewCommentList = new ArrayList<>();

    private String reviewTitle;
    private String reviewContent;
    private String courseName;
    private Integer courseEvaluation;

    private Boolean status;

    @Column(updatable = false, nullable = false)
    private Date createdAt;

    private Date updatedAt;

    private Integer viewCnt;
    private Integer upCnt;
    private Integer scrapCnt;
    private Integer commentCnt;

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
