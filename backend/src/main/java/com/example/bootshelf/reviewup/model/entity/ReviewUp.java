package com.example.bootshelf.reviewup.model.entity;

import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.reviewup.model.request.PostCreateReviewUpReq;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Review_idx")
    private Review review;

    @Column(nullable = false)
    private Boolean status;

    public static ReviewUp toEntity(User user, PostCreateReviewUpReq req) {
        return ReviewUp.builder()
                .user(User.builder().idx(user.getIdx()).build())
                .review(Review.builder().idx(req.getReviewIdx()).build())
                .status(true)
                .build();
    }
}
