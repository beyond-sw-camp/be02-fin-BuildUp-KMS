package com.example.bootshelf.review.repository.querydsl;

import com.example.bootshelf.review.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepositoryCustom {

    Page<Review> findMyReviewList(Integer userIdx, Pageable pageable);

    Page<Review> findReviewList(Integer reviewCategoryIdx, Integer sortType, Pageable pageable);

}
