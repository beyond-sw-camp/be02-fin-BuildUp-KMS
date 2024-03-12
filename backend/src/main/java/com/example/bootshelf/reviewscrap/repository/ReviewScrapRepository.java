package com.example.bootshelf.reviewscrap.repository;

import com.example.bootshelf.reviewscrap.model.ReviewScrap;
import com.example.bootshelf.reviewscrap.repository.querydsl.ReviewScrapRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewScrapRepository extends JpaRepository<ReviewScrap, Integer>, ReviewScrapRepositoryCustom {
    Optional<ReviewScrap> findByIdx(Integer idx);
}
