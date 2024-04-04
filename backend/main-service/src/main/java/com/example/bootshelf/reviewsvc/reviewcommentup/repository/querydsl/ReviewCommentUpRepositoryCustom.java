package com.example.bootshelf.reviewsvc.reviewcommentup.repository.querydsl;

import com.example.bootshelf.reviewsvc.reviewcommentup.model.entity.ReviewCommentUp;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewCommentUpRepositoryCustom {
    Page<ReviewCommentUp> findByUser(User user, Pageable pageable);
    ReviewCommentUp findByUserIdxAndReviewCommentIdx(Integer userIdx, Integer reviewCommentIdx);
}
