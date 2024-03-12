package com.example.bootshelf.reviewcomment.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateReviewCommentRes {
    private Integer idx;
    private Integer reviewIdx;
    private Integer userIdx;
    private String nickName;
    private Integer reviewcommentIdx;
    private String reviewCommentContent;
    private String createAt;
}
