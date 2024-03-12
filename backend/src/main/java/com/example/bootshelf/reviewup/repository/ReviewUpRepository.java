package com.example.bootshelf.reviewup.repository;

import com.example.bootshelf.reviewup.model.entity.ReviewUp;
import com.example.bootshelf.reviewup.repository.querydsl.ReviewUpRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewUpRepository extends JpaRepository<ReviewUp, Integer>, ReviewUpRepositoryCustom {
    Optional<ReviewUp> findByIdx(Integer idx);
}
