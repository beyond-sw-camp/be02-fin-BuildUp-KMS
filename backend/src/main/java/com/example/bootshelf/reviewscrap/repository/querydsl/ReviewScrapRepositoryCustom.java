package com.example.bootshelf.reviewscrap.repository.querydsl;

import com.example.bootshelf.reviewscrap.model.entity.ReviewScrap;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewScrapRepositoryCustom {
    Page<ReviewScrap> findByUser(User user, Pageable pageable);
    ReviewScrap findByUserIdxAndReviewIdx(Integer userIdx, Integer reviewIdx);
}
