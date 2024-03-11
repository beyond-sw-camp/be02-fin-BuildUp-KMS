package com.example.bootshelf.boardcategory.repository;

import com.example.bootshelf.boardcategory.model.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Integer> {
}
