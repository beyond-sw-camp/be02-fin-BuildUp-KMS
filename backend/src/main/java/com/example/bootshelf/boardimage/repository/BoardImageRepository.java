package com.example.bootshelf.boardimage.repository;

import com.example.bootshelf.boardimage.model.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardImageRepository extends JpaRepository<BoardImage, Integer> {
}
