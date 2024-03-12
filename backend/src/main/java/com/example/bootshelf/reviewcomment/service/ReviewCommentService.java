package com.example.bootshelf.reviewcomment.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.reviewcomment.model.ReviewComment;
import com.example.bootshelf.reviewcomment.model.response.PostCreateReviewCommentRes;
import com.example.bootshelf.reviewcomment.repository.ReviewCommentRepository;
import com.example.bootshelf.reviewcomment.model.request.PostCreateReviewCommentReq;
import com.example.bootshelf.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ReviewCommentService {
    private final ReviewCommentRepository reviewCommentRepository;

    @Transactional(readOnly = false)
    public BaseRes createComment(User user, Integer reviewIdx, PostCreateReviewCommentReq postCreateReviewCommentReq) {

        ReviewComment reviewComment = ReviewComment.builder()
                .user(user)
                .review(Review.builder().idx(reviewIdx).build())
                .reviewCommentContent(postCreateReviewCommentReq.getReviewCommentContent())
                .status(true)
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        reviewComment = reviewCommentRepository.save(reviewComment);

        PostCreateReviewCommentRes postCreateReviewCommentRes = PostCreateReviewCommentRes.builder()
                .idx(reviewComment.getIdx())
                .reviewIdx(reviewComment.getReview().getIdx())
                .userIdx(reviewComment.getUser().getIdx())
                .nickName(reviewComment.getUser().getNickName())
                .reviewCommentContent(postCreateReviewCommentReq.getReviewCommentContent())
                .createAt(reviewComment.getCreatedAt())
                .build();


        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기글 댓글 등록 성공")
                .result(postCreateReviewCommentRes)
                .build();

        return baseRes;
    }
}
