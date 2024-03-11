package com.example.bootshelf.user.model.entity;

import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.reviewcomment.model.ReviewComment;
import com.example.bootshelf.reviewcommentup.model.ReviewCommentUp;
import com.example.bootshelf.reviewhistory.model.ReviewHistory;
import com.example.bootshelf.reviewscrap.model.ReviewScrap;
import com.example.bootshelf.reviewup.model.ReviewUp;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @OneToMany(mappedBy = "userIdx")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "userIdx")
    private List<ReviewUp> reviewUpList = new ArrayList<>();

    @OneToMany(mappedBy = "userIdx")
    private List<ReviewScrap> reviewScrapList = new ArrayList<>();

    @OneToMany(mappedBy = "userIdx")
    private List<ReviewHistory> reviewHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "userIdx")
    private List<ReviewComment> reviewCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "userIdx")
    private List<ReviewCommentUp> reviewCommentUpList = new ArrayList<>();
}
