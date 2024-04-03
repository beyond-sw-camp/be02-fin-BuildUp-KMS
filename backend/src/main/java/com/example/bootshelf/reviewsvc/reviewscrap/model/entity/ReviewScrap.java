package com.example.bootshelf.reviewsvc.reviewscrap.model.entity;

import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import com.example.bootshelf.reviewsvc.reviewscrap.model.request.PostCreateReviewScrapReq;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewScrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Review_idx")
    private Review review;

    private Boolean status;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static ReviewScrap toEntity(User user, PostCreateReviewScrapReq req) {
        return ReviewScrap.builder()
                .user(User.builder().idx(user.getIdx()).build())
                .review(Review.builder().idx(req.getReviewIdx()).build())
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
