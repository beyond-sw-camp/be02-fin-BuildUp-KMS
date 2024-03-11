package com.example.bootshelf.boardcomment.repository;

import com.example.bootshelf.boardcomment.model.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {
}
