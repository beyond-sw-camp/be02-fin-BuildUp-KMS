package com.example.bootshelf.boardsvc.boardtag.repository;

import com.example.bootshelf.boardsvc.boardtag.model.entity.BoardTag;
import com.example.bootshelf.boardsvc.boardtag.repository.querydsl.BoardTagRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardTagRepository extends JpaRepository<BoardTag, Integer>, BoardTagRepositoryCustom {
    void deleteAllByBoard_Idx(Integer idx);

    Integer deleteAllByTag_Idx(Integer idx);
}
