package com.example.bootshelf.board.repository;

import com.example.bootshelf.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, String> {
    Optional<Board> findByBoardTitle(String boardTitle);
}
