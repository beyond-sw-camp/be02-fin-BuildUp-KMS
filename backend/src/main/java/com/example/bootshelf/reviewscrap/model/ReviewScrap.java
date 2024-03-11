package com.example.bootshelf.reviewscrap.model;

import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.reviewscrap.model.request.PostCreateReviewScrapReq;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "User_idx")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Review_idx")
    private Review review;

    private Boolean status;

    @Column(updatable = false, nullable = false)
    private String createdAt;

    private String updatedAt;

    public static ReviewScrap toEntity(User user, PostCreateReviewScrapReq req) {
        return ReviewScrap.builder()
                .user(User.builder().idx(user.getIdx()).build())
                .review(Review.builder().idx(req.getReviewIdx()).build())
                .status(true)
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .build();
    }
}
