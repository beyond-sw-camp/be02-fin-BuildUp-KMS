package com.example.bootshelf.boardsvc.boardcategory.repository;

import com.example.bootshelf.boardsvc.boardcategory.model.entity.BoardCategory;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Integer> {
    Optional<BoardCategory> findByIdx(Integer idx);

}
