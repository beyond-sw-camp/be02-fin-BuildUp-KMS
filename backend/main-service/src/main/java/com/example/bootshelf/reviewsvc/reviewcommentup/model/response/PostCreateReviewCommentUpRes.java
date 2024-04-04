package com.example.bootshelf.reviewsvc.reviewcommentup.model.response;

import com.example.bootshelf.reviewsvc.reviewcommentup.model.entity.ReviewCommentUp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateReviewCommentUpRes {
    private Integer reviewCommentUpIdx;
    private Integer userIdx;
    private Integer reviewCommentIdx;

    public static PostCreateReviewCommentUpRes toDto(ReviewCommentUp reviewCommentUp) {
        return PostCreateReviewCommentUpRes.builder()
                .reviewCommentUpIdx(reviewCommentUp.getIdx())
                .userIdx(reviewCommentUp.getUser().getIdx())
                .reviewCommentIdx(reviewCommentUp.getReviewComment().getIdx())
                .build();
    }
}