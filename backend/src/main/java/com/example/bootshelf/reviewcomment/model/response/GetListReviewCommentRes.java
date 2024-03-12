package com.example.bootshelf.reviewcomment.model.response;

import lombok.*;

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
}
