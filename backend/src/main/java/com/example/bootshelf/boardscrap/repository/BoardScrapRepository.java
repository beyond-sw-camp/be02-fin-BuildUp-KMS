package com.example.bootshelf.boardscrap.repository;

import com.example.bootshelf.boardscrap.model.entity.BoardScrap;
import com.example.bootshelf.boardscrap.repository.querydsl.BoardScrapRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardScrapRepository extends JpaRepository<BoardScrap, Integer>, BoardScrapRepositoryCustom {
    Optional<BoardScrap> findByIdx(Integer idx);
}
