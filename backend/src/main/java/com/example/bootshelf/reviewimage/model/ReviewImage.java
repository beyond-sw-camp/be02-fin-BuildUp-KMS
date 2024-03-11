package com.example.bootshelf.reviewimage.model;

import com.example.bootshelf.review.model.entity.Review;
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

    @ManyToOne
    @JoinColumn(name = "Review_idx")
    private Review reviewIdx;

    private String reviewImage;
    private Boolean status;
}
