package com.example.bootshelf.reviewsvc.reviewimage.model.entity;

import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Review_idx")
    private Review review;

    @Column(nullable = false, length = 500)
    private String reviewImage;

    @Column(nullable = false)
    private Boolean status;
}
