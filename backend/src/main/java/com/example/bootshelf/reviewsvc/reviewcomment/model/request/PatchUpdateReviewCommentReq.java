package com.example.bootshelf.reviewsvc.reviewcomment.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateReviewCommentReq {


    @NotNull
    @Length(min=1, max=200)
    @ApiModelProperty(value = "댓글내용(200자 이하)", example = "이 글보고 부트캠프 신청했어요!", required = true)
    private String reviewCommentContent;
}
