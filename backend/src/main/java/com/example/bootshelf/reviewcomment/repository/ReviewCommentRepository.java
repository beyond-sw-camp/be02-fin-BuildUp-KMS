package com.example.bootshelf.reviewcomment.repository;

import com.example.bootshelf.reviewcomment.model.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer> {

    List<ReviewComment> findByReviewIdx(Integer reviewIdx);

    Optional<ReviewComment> findByIdx(Integer idx);





}
