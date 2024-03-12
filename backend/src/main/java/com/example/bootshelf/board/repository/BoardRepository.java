package com.example.bootshelf.board.repository;

import com.example.bootshelf.board.model.entity.Board;
import com.example.bootshelf.boardscrap.model.entity.BoardScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Optional<Board> findByIdx(Integer idx);
}
