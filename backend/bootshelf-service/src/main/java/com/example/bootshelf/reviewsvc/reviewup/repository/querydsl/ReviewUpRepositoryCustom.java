package com.example.bootshelf.reviewsvc.reviewup.repository.querydsl;

import com.example.bootshelf.reviewsvc.reviewup.model.entity.ReviewUp;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewUpRepositoryCustom {
    Page<ReviewUp> findByUser(User user, Pageable pageable);
    ReviewUp findByUserIdxAndReviewIdx(Integer userIdx, Integer reviewIdx);
}
