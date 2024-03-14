package com.example.bootshelf.boardsvc.boardcommentup.repository;

import com.example.bootshelf.boardsvc.boardcommentup.model.entity.BoardCommentUp;
import com.example.bootshelf.boardsvc.boardcommentup.repository.querydsl.BoardCommentUpRepositoryCustom;
import com.example.bootshelf.boardsvc.boardup.model.entity.BoardUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardCommentUpRepository extends JpaRepository<BoardCommentUp, Integer>, BoardCommentUpRepositoryCustom {
    Optional<BoardCommentUp> findByIdx(Integer idx);

}
