package com.example.bootshelf.comment.service;

import com.example.bootshelf.comment.model.entity.Comment;
import com.example.bootshelf.comment.model.request.PatchUpdateCommentReq;
import com.example.bootshelf.comment.model.request.PostCreateCommentReq;
import com.example.bootshelf.comment.model.response.GetListCommentRes;
import com.example.bootshelf.comment.model.response.PatchUpdateCommentRes;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


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
                .code(200)
                .isSuccess(true)
                .message("댓글 등록 성공")
                .result(createCommentResponse)
                .build();

        return baseRes;
    }

    @Transactional(readOnly = true)
    public BaseRes listComment(Integer reviewIdx) {

        List<Comment> commentList = commentRepository.findByReview_ReviewIdx(reviewIdx);
        List<GetListCommentRes> response = new ArrayList<>();

        for (Comment comment : commentList) {

            GetListCommentRes getListCommentRes = GetListCommentRes.builder()
//                    .userNickName(comment.getUser().getUserNickName())
                    .reviewCommnetContent(comment.getReviewCommentContent())
                    .createAt(comment.getCreatedAt())
                    .updateAt(comment.getUpdatedAt())
                    .build();
            response.add(getListCommentRes);
        }

        BaseRes baseRes = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(response)
                .build();

        return baseRes;

    }

    @Transactional(readOnly = false)
    public BaseRes updateComment(Integer reviewIdx, PatchUpdateCommentReq patchUpdateCommentReq, User user) {

        Optional<Comment> result = commentRepository.findByCommentIdx(patchUpdateCommentReq.getCommentIdx());

        if (result.isPresent()) {
         Comment comment = result.get();
            if(Objects.equals(comment.getReview().getIdx(), reviewIdx)){

                comment.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
                commentRepository.save(comment);

                PatchUpdateCommentRes patchUpdateCommentRes = PatchUpdateCommentRes.builder()
                        .reviewCommentContent(comment.getReviewCommentContent())
                        .updateAt(comment.getUpdatedAt())
                        .build();

                BaseRes baseRes = BaseRes.builder()
                        .code(200)
                        .isSuccess(true)
                        .message("댓글 수정 성공")
                        .result(patchUpdateCommentRes)
                        .build();

                return baseRes;

            }

        } else {
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("댓글 수정 실패")
                    .result("잘못된 요청입니다.")
                    .build();
        }
        return null;
    }

    @Transactional(readOnly = false)
    public BaseRes deleteComment(Integer reviewIdx, Integer commentIdx, User user) {
        Integer result = commentRepository.deleteByCommentIdxAndUser_userIdx(commentIdx, user.getIdx());

        if(!result.equals(0)) {
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("댓글 삭제 성공")
                    .result(commentIdx)
                    .build();
        }
        return BaseRes.builder()
                .code(400)
                .isSuccess(false)
                .message("댓글 삭제 실패")
                .result("잘못된 요청입니다.")
                .build();
    }

}




