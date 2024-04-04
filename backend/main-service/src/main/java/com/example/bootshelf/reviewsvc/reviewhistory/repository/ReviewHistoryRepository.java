package com.example.bootshelf.reviewsvc.reviewhistory.repository;

import com.example.bootshelf.reviewsvc.reviewhistory.model.ReviewHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewHistoryRepository extends JpaRepository<ReviewHistory, Integer> {
}
