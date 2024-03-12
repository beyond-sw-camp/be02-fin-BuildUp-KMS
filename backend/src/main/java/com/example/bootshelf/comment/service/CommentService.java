package com.example.bootshelf.comment.service;

import com.example.bootshelf.comment.model.entity.Comment;
import com.example.bootshelf.comment.model.request.PostCreateCommentReq;
import com.example.bootshelf.comment.model.response.PostCreateCommentRes;
import com.example.bootshelf.comment.repository.CommentRepository;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional(readOnly = false)
    public BaseRes createComment(User user, Integer reviewIdx, PostCreateCommentReq postCreateCommentReq) {

        commentRepository.save(Comment.builder()
                .review(Review.builder().idx(reviewIdx).build())
                .user(user)
                .reviewCommentContent(postCreateCommentReq.getReviewCommentContent())
                .status(true)
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build());

        PostCreateCommentRes createCommentResponse = PostCreateCommentRes.builder()
                .reviewCommentContent(postCreateCommentReq.getReviewCommentContent())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("후기글 댓글 등록 성공")
                .result(createCommentResponse)
                .build();

        return baseRes;
    }
}
