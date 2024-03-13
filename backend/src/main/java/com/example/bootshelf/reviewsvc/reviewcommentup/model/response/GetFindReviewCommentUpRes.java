package com.example.bootshelf.reviewsvc.reviewcommentup.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetFindReviewCommentUpRes {
    private Integer reviewCommentUpIdx;
    private Integer userIdx;
    private Integer reviewCommentIdx;
}