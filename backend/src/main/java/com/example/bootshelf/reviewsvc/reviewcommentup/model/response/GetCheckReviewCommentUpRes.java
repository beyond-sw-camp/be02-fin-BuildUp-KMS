package com.example.bootshelf.reviewsvc.reviewcommentup.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCheckReviewCommentUpRes {
    private Integer reviewCommentUpIdx;
    private Boolean status;
}