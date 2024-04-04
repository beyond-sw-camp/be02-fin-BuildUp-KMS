package com.example.bootshelf.reviewsvc.reviewcomment.model.response;

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
    private String userImg;
    private String userNickName;
    private String reviewCommnetContent;
    private String createAt;
    private String updateAt;
    private Boolean status;
    private List<GetListReviewCommentRes> children; // 자식 댓글 목록

}
