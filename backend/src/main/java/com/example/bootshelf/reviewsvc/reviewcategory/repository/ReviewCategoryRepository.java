package com.example.bootshelf.reviewsvc.reviewcategory.repository;

import com.example.bootshelf.boardsvc.boardcategory.model.entity.BoardCategory;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import com.example.bootshelf.reviewsvc.reviewcategory.model.ReviewCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewCategoryRepository extends JpaRepository<ReviewCategory, Integer> {

    Optional<ReviewCategory> findByIdx(Integer idx);
    Optional<ReviewCategory> findByCategoryName(String categoryName );




}
