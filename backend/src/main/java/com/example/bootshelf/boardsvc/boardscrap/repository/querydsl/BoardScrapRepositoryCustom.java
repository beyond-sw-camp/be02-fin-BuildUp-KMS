package com.example.bootshelf.boardsvc.boardscrap.repository.querydsl;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.boardscrap.model.entity.BoardScrap;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardScrapRepositoryCustom {
    Page<BoardScrap> findByUser(User user, Pageable pageable);
    BoardScrap findByUserIdxAndBoardIdx(Integer userIdx, Integer boardIdx);

    Page<BoardScrap> findBoardScrapListByCategory (User user, Integer boardCategoryIdx, Integer sortIdx, Pageable pageable);
}
