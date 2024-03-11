package com.example.bootshelf.comment.repository;

import com.example.bootshelf.comment.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByReview_ReviewIdx(Integer reviewIdx);

    Optional<Comment> findByCommentIdx(Integer commentIdx);

    Integer deleteByCommentIdxAndUser_userIdx(Integer commentIdx, Integer userIdx);




}
