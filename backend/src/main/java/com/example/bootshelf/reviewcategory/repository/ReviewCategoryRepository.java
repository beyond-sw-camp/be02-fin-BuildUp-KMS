package com.example.bootshelf.reviewcategory.repository;

import com.example.bootshelf.reviewcategory.model.ReviewCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewCategoryRepository extends JpaRepository<ReviewCategory, Integer> {
}
