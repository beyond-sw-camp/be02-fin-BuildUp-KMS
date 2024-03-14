package com.example.bootshelf.reviewsvc.review.repository.querydsl;

import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepositoryCustom {

    Page<Review> findMyReviewList(Integer userIdx, Pageable pageable);

    Page<Review> findReviewList(Integer reviewCategoryIdx, Integer sortType, Pageable pageable);

    Page<Review> findReviewsBySearchTerm(Integer sortType, String searchTerm, Pageable pageable);

}
