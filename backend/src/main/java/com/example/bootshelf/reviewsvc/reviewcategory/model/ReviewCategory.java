package com.example.bootshelf.reviewsvc.reviewcategory.model;

import com.example.bootshelf.reviewsvc.review.model.entity.Review;
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
public class ReviewCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String categoryName;
    private Boolean status;
    private String createdAt;
    private String updatedAt;

    @OneToMany(mappedBy = "reviewCategory")
    private List<Review> reviewList = new ArrayList<>();
}
