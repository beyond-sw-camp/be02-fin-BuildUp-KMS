package com.example.bootshelf.reviewsvc.reviewcommentup.repository;

import com.example.bootshelf.reviewsvc.reviewcommentup.model.ReviewCommentUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewCommentUpRepository extends JpaRepository<ReviewCommentUp, Integer> {
}
