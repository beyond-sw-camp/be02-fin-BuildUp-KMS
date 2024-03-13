package com.example.bootshelf.boardsvc.boardcommentup.repository;

import com.example.bootshelf.boardsvc.boardcommentup.model.entity.BoardCommentUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCommentUpRepository extends JpaRepository<BoardCommentUp, Integer> {
}