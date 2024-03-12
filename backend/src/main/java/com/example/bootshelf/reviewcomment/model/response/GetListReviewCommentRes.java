package com.example.bootshelf.reviewcomment.model.response;

import com.example.bootshelf.review.model.response.GetListCommentReviewRes;
import com.example.bootshelf.reviewcomment.model.entity.ReviewComment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetListReviewCommentRes {
    private Integer idx;
    private Integer reviewIdx;
    private Integer userIdx;
    private String userNickName;
    private String reviewCommnetContent;
    private String createAt;
    private String updateAt;
    private List<GetListReviewCommentRes> children; // 자식 댓글 목록

}
