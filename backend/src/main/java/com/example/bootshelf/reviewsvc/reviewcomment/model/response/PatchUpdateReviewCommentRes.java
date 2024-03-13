package com.example.bootshelf.reviewsvc.reviewcomment.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateReviewCommentRes {

    private Integer idx;
    private Integer userIdx;
    private String nickName;
    private String reviewCommentContent;
    private String createAt;
    private String updateAt;

}
