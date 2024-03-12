package com.example.bootshelf.boardup.repository;

import com.example.bootshelf.boardscrap.model.entity.BoardScrap;
import com.example.bootshelf.boardup.model.entity.BoardUp;
import com.example.bootshelf.boardup.repository.querydsl.BoardUpRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardUpRepository extends JpaRepository<BoardUp, Integer>, BoardUpRepositoryCustom {
    Optional<BoardUp> findByIdx(Integer idx);

}
