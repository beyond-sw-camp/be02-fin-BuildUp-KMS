package com.example.bootshelf.boardscrap.repository;

import com.example.bootshelf.boardscrap.model.entity.BoardScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardScrapRepositorty extends JpaRepository<BoardScrap, Integer> {
}
