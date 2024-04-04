package com.example.bootshelf.boardsvc.boardcommentup.repository.querydsl;

import com.example.bootshelf.boardsvc.boardcommentup.model.entity.BoardCommentUp;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCommentUpRepositoryCustom {
    Page<BoardCommentUp> findByUser(User user, Pageable pageable);
    BoardCommentUp findByUserIdxAndBoardCommentIdx(Integer userIdx, Integer boardCommentIdx);
}
