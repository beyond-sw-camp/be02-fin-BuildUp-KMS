package com.example.bootshelf.boardcomment.repository;

import com.example.bootshelf.boardcomment.model.entity.BoardComment;
import com.example.bootshelf.reviewcomment.model.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {

    Optional<BoardComment> findByIdx(Integer idx);

    List<BoardComment> findByBoardIdxAndParentIsNull(Integer boardIdx);

}
