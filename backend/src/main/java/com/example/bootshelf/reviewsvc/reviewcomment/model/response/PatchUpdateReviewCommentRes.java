package com.example.bootshelf.reviewsvc.reviewcomment.model.response;

import lombok.*;
import org.joda.time.LocalDateTime;

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
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
