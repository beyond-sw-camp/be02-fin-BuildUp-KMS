package com.example.bootshelf.reviewsvc.reviewcomment.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateReviewCommentRes {
    private String reviewCommentContent;
}
