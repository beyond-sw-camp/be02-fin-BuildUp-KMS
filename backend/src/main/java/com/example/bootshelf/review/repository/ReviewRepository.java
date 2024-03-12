package com.example.bootshelf.review.repository;

import com.example.bootshelf.review.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Review> findByIdx(Integer idx);
}
