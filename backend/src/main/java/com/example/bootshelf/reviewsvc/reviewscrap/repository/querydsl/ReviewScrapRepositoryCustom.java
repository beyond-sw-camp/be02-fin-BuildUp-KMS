package com.example.bootshelf.reviewsvc.reviewscrap.repository.querydsl;

import com.example.bootshelf.reviewsvc.reviewscrap.model.entity.ReviewScrap;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewScrapRepositoryCustom {
    Page<ReviewScrap> findByUser(User user, Pageable pageable);
    ReviewScrap findByUserIdxAndReviewIdx(Integer userIdx, Integer reviewIdx);

    Page<ReviewScrap> findByUserAndCategoryIdx(User user, Integer reviewCategoryIdx, Integer sortIdx, Pageable pageable);
}
