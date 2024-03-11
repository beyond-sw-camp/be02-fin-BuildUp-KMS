package com.example.bootshelf.reviewimage.repository;

import com.example.bootshelf.reviewimage.model.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewImageRepository extends JpaRepository<ReviewImage, Integer> {
}
