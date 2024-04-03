package com.example.bootshelf.reviewsvc.reviewcomment.repository;

import com.example.bootshelf.boardsvc.boardcomment.model.entity.BoardComment;
import com.example.bootshelf.reviewsvc.reviewcomment.model.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer> {

    Optional<ReviewComment> findByIdx(Integer idx);

    List<ReviewComment> findByReviewIdxAndParentIsNull(Integer reviewIdx);

    Optional<ReviewComment> findByIdxAndUserIdx(Integer reviewIdx, Integer userIdx);









}
