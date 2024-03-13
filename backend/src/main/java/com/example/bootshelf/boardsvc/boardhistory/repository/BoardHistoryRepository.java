package com.example.bootshelf.boardsvc.boardhistory.repository;

import com.example.bootshelf.boardsvc.boardhistory.model.entity.BoardHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardHistoryRepository extends JpaRepository<BoardHistory, Integer> {
}
