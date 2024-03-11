package com.example.bootshelf.comment.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchUpdateCommentReq {

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "댓글 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer commentIdx;

    @NotNull
    @Length(min=1, max=200)
    @ApiModelProperty(value = "댓글내용(200자 이하)", example = "이 글보고 부트캠프 신청했어요!", required = true)
    private String reviewCommentContent;

}
