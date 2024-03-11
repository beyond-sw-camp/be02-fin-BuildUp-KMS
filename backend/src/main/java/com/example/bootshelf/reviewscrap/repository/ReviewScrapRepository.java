package com.example.bootshelf.reviewscrap.repository;

import com.example.bootshelf.reviewscrap.model.ReviewScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewScrapRepository extends JpaRepository<ReviewScrap, Integer> {
}
