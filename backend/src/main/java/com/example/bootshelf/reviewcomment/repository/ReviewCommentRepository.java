package com.example.bootshelf.reviewcomment.repository;

import com.example.bootshelf.reviewcomment.model.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer> {
}
