package com.example.bootshelf.boardsvc.board.repository;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.board.repository.querydsl.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>, BoardRepositoryCustom, QuerydslPredicateExecutor<Board> {
    Optional<Board> findByBoardTitle(String boardTitle);
    Optional<Board> findByIdx(Integer boardIdx);

    Optional<Board> findByIdxAndUserIdx(Integer boardIdx, Integer userIdx);

}
