package com.example.bootshelf.boardsvc.boardcomment.repository;

import com.example.bootshelf.boardsvc.boardcomment.model.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {

    Optional<BoardComment> findByIdx(Integer idx);
    Optional<BoardComment> findByIdxAndUserIdx(Integer commentidx, Integer userIdx);


    List<BoardComment> findByBoardIdxAndParentIsNull(Integer boardIdx);

}
