package com.example.bootshelf.reviewcomment.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateReviewCommentReq {


    @NotNull(message = "후기글 댓글은 필수 입력 항목입니다.")
    @Length(min=1, max=400, message = "후기글 댓글의 내용은 최소 1글자 이상, 최대 100자 이하여야 합니다.")
    @ApiModelProperty(value = "후기글 댓글 내용(100자 이하)", example = "이 글이 너무 도움이 됐습니다!", required = true)
    private String reviewCommentContent;

}
