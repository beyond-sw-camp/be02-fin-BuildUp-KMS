package com.example.bootshelf.boardsvc.board.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateBoardReq {

    @NotNull(message = "게시글 카테고리 IDX는 필수 입력 항목입니다.")
    @Min(value = 1, message = "게시글 카테고리 IDX는 최소 1 이상의 숫자여야 합니다.")
    @ApiModelProperty(value = "게시글 카테고리 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer boardCategoryIdx;

    @NotNull(message = "게시글 제목은 필수 입력 항목입니다.")
    @Length(min=1, max=100, message = "게시글 제목은 최소 1글자 이상, 최대 100자 이하여야 합니다.")
    @ApiModelProperty(value = "후기 제목(100자 이하)", example = "코딩 테스트 준비 어떻게 하시나요?", required = true)
    private String boardTitle;

    @NotNull(message = "게시글 내용은 필수 입력 항목입니다.")
    @Length(min=1, message = "게시글 내용은 최소 1글자 이상이어야 합니다.")
    @ApiModelProperty(value = "게시글 내용", example = "백준이나 프로그래머스 중 모가 좋을까요...", required = true)
    private String boardContent;

    private List<String> tagList;
}
