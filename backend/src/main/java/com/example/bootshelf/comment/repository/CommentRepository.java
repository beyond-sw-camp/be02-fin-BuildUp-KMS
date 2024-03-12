package com.example.bootshelf.comment.repository;

import com.example.bootshelf.comment.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
