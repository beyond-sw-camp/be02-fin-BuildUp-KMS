package com.example.bootshelf.reviewsvc.review.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListCommentReviewRes {

    private Integer commentIdx;
    private Integer userIdx;
    private String userNickName;
    private String reviewCommentContent;
    private Integer upCnt;
    private String updatedAt;
    private List<GetListCommentReviewRes> children; // 대댓글 목록을 위한 필드 추가
}
