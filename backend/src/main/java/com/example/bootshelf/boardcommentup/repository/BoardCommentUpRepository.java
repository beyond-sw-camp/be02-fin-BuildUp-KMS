package com.example.bootshelf.boardcommentup.repository;

import com.example.bootshelf.boardcommentup.model.entity.BoardCommentUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCommentUpRepository extends JpaRepository<BoardCommentUp, Integer> {
}
