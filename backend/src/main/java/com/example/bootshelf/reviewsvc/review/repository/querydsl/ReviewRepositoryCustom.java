package com.example.bootshelf.reviewsvc.review.repository.querydsl;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.reviewsvc.review.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepositoryCustom {

    Page<Review> findMyReviewList(Integer userIdx, Pageable pageable, Integer reviewCategoryIdx, Integer sortType);

    Page<Review> findReviewList(Integer reviewCategoryIdx, Integer sortType, Pageable pageable);

    Page<Review> findReviewsBySearchTerm(Integer reviewCategoryIdx, Integer sortType, String searchTerm, Pageable pageable);

    Page<Review> searchReviewListByQuery(Pageable pageable, String query, Integer searchType);

    Page<Review> searchReviewListBySortType(String query, Integer searchType, Integer sortType, Pageable pageable);

    Page<Review> searchReviewListByQueryV2(Pageable pageable, String query, Integer searchType);
}
