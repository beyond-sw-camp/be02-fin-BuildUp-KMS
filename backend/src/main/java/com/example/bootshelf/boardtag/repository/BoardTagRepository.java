package com.example.bootshelf.boardtag.repository;

import com.example.bootshelf.boardtag.model.entity.BoardTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardTagRepository extends JpaRepository<BoardTag, Integer> {
}
