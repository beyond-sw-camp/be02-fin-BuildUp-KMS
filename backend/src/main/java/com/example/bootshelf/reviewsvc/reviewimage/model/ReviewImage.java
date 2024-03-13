package com.example.bootshelf.reviewsvc.reviewimage.model;

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

    private String reviewImage;
    private Boolean status;
}
