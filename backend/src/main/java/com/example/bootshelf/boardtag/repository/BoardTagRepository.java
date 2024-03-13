package com.example.bootshelf.boardtag.repository;

import com.example.bootshelf.boardtag.model.entity.BoardTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardTagRepository extends JpaRepository<BoardTag, Integer> {
    Optional<BoardTag> findByBoardIdx (Integer boardIdx);
    List<BoardTag> findAllByIdx (Integer boardIdx);
}
