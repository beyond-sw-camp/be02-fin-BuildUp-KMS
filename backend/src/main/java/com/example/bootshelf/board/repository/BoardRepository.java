package com.example.bootshelf.board.repository;

import com.example.bootshelf.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
    Optional<Board> findByBoardTitle(String boardTitle);
    Optional<Board> findByIdx(Integer boardIdx);

    Optional<Board> findByIdxAndUserIdx(Integer boardIdx, Integer userIdx);


}
