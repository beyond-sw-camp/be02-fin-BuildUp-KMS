package com.example.bootshelf.reviewsvc.reviewcategory.repository;

import com.example.bootshelf.reviewsvc.reviewcategory.model.ReviewCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewCategoryRepository extends JpaRepository<ReviewCategory, Integer> {
}
