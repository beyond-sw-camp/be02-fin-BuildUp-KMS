package com.example.bootshelf.boardsvc.boardup.repository.querydsl;

import com.example.bootshelf.boardsvc.boardup.model.entity.BoardUp;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardUpRepositoryCustom {
    Page<BoardUp> findByUser(User user, Pageable pageable);
    BoardUp findByUserIdxAndBoardIdx(Integer userIdx, Integer boardIdx);
}
