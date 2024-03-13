package com.example.bootshelf.reviewsvc.reviewcommentup.repository;

import com.example.bootshelf.reviewsvc.reviewcommentup.model.entity.ReviewCommentUp;
import com.example.bootshelf.reviewsvc.reviewcommentup.repository.querydsl.ReviewCommentUpRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewCommentUpRepository extends JpaRepository<ReviewCommentUp, Integer>, ReviewCommentUpRepositoryCustom {
    Optional<ReviewCommentUp> findByIdx(Integer idx);

}
