package com.example.bootshelf.review.model.entity;

import com.example.bootshelf.review.model.request.PatchUpdateReviewReq;
import com.example.bootshelf.reviewcategory.model.ReviewCategory;
import com.example.bootshelf.reviewcomment.model.entity.ReviewComment;
import com.example.bootshelf.reviewhistory.model.ReviewHistory;
import com.example.bootshelf.reviewimage.model.ReviewImage;
import com.example.bootshelf.reviewscrap.model.ReviewScrap;
import com.example.bootshelf.reviewup.model.ReviewUp;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    private User user;

    @ManyToOne
    @JoinColumn(name = "ReviewCategory_idx")
    private ReviewCategory reviewCategory;

    @OneToMany(mappedBy = "review")
    private List<ReviewUp> reviewUpList = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    private List<ReviewScrap> reviewScrapList = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    private List<ReviewImage> reviewImageList = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    private List<ReviewHistory> reviewHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    private List<ReviewComment> reviewCommentList = new ArrayList<>();

    @Column(nullable = false, length = 100)
    private String reviewTitle;

    @Column(nullable = false, length = 400)
    private String reviewContent;

    @Column(nullable = false, length = 200)
    private String courseName;

    @Column(nullable = false)
    private Integer courseEvaluation;

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

    public void increaseViewCount() {
        this.viewCnt = this.viewCnt + 1;
    }

    public void update(PatchUpdateReviewReq patchUpdateReviewReq) {
        if (patchUpdateReviewReq.getReviewTitle() != null) {
            this.reviewTitle = patchUpdateReviewReq.getReviewTitle();
        }
        if (patchUpdateReviewReq.getReviewContent() != null) {
            this.reviewContent = patchUpdateReviewReq.getReviewContent();
        }
        if (patchUpdateReviewReq.getCourseEvaluation() != null) {
            this.courseEvaluation = patchUpdateReviewReq.getCourseEvaluation();
        }
    }
}
