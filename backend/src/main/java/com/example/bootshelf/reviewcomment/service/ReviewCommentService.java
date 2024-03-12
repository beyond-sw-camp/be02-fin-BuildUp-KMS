package com.example.bootshelf.reviewcomment.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.reviewcomment.model.entity.ReviewComment;
import com.example.bootshelf.reviewcomment.model.response.PostCreateReviewCommentRes;
import com.example.bootshelf.reviewcomment.repository.ReviewCommentRepository;
import com.example.bootshelf.reviewcomment.model.request.PostCreateReviewCommentReq;
import com.example.bootshelf.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.bootshelf.reviewcomment.model.QReviewComment.reviewComment;

@Service
@RequiredArgsConstructor
public class ReviewCommentService {
    private final ReviewCommentRepository reviewCommentRepository;

    @Transactional(readOnly = false)
    public BaseRes createComment(User user, Integer reviewIdx, PostCreateReviewCommentReq postCreateReviewCommentReq) {

        reviewCommentRepository.save(ReviewComment.builder()
                .review(Review.builder().idx(reviewIdx).build())
                .user(user)
                .reviewCommentContent(postCreateReviewCommentReq.getReviewCommentContent())
                .status(true)
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build());

        PostCreateReviewCommentRes postCreateReviewCommentRes = PostCreateReviewCommentRes.builder()
                .reviewCommentContent(postCreateReviewCommentReq.getReviewCommentContent())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("댓글 등록 성공")
                .result(postCreateReviewCommentRes)
                .build();

        return baseRes;

    }
}
