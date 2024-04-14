package com.example.bootshelf.boardsvc.boardcomment.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateBoardCommentReq {


    @NotNull
    @Length(min=1)
    @ApiModelProperty(value = "댓글 내용", example = "이 글보고 부트캠프 신청했어요!", required = true)
    private String boardCommentContent;
}
