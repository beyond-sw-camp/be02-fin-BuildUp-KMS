package com.example.bootshelf.boardsvc.board.repository.querydsl;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepositoryCustom {
    Page<Board> findMyBoardList(Integer userIdx, Pageable pageable, Integer sortIdx);

    Page<Board> findMyBoardListByCategory(Integer userIdx, Pageable pageable, Integer categoryIdx, Integer sortIdx);

    Page<Board> findBoardListByCategory(Pageable pageable, Integer categoryIdx, Integer sortIdx);

    Page<Board> findBoardListByTag(Pageable pageable, Integer TagIdx, Integer sortIdx);

    Page<Board> searchBoardListByQuery(Pageable pageable, String keyword, Integer searchType);


    Page<Board> searchBoardListByQueryV2(Pageable pageable, String query, Integer searchType);

    Page<Board> searchBoardListByQueryAndCategory(Pageable pageable, String query, Integer boardCategoryIdx, Integer sortIdx);
}
