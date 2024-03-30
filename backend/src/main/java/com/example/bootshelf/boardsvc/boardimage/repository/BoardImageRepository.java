package com.example.bootshelf.boardsvc.boardimage.repository;

import com.example.bootshelf.boardsvc.boardimage.model.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardImageRepository extends JpaRepository<BoardImage, Integer> {

    void deleteAllByBoard_idx(Integer boardIdx);
}
