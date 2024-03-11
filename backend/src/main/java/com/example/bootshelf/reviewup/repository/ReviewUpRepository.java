package com.example.bootshelf.reviewup.repository;

import com.example.bootshelf.reviewup.model.ReviewUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewUpRepository extends JpaRepository<ReviewUp, Integer> {
}
