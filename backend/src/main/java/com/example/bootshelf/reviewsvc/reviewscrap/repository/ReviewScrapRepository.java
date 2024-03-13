package com.example.bootshelf.reviewsvc.reviewscrap.repository;

import com.example.bootshelf.reviewsvc.reviewscrap.model.entity.ReviewScrap;
import com.example.bootshelf.reviewsvc.reviewscrap.repository.querydsl.ReviewScrapRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewScrapRepository extends JpaRepository<ReviewScrap, Integer>, ReviewScrapRepositoryCustom {
    Optional<ReviewScrap> findByIdx(Integer idx);
}
