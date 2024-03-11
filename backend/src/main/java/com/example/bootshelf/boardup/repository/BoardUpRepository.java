package com.example.bootshelf.boardup.repository;

import com.example.bootshelf.boardup.model.entity.BoardUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardUpRepository extends JpaRepository<BoardUp, Integer> {
}
