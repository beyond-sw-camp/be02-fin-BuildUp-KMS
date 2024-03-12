package com.example.bootshelf.review.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateReviewReq {

    @NotNull(message = "후기 카테고리 IDX는 필수 입력 항목입니다.")
    @Min(value = 1, message = "후기 카테고리 IDX는 최소 1 이상의 숫자여야 합니다.")
    @ApiModelProperty(value = "후기 카테고리 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer reviewCategoryIdx;

    @NotNull(message = "후기 제목은 필수 입력 항목입니다.")
    @Length(min=1, max=100, message = "후기 제목은 최소 1글자 이상, 최대 100자 이하여야 합니다.")
    @ApiModelProperty(value = "후기 제목(100자 이하)", example = "한화시스템 BEYOND SW 캠프 후기입니다.", required = true)
    private String reviewTitle;

    @NotNull(message = "후기 내용은 필수 입력 항목입니다.")
    @Length(min=1, max=400, message = "후기 내용은 최소 1글자 이상, 최대 400자 이하여야 합니다.")
    @ApiModelProperty(value = "후기 내용(400자 이하)", example = "커리큘럽도 정말 잘 편성되어 있고, 강사님도 정말 좋으셨습니다!", required = true)
    private String reviewContent;

    @NotNull(message = "과정명은 필수 입력 항목입니다.")
    @Length(min=1, max=200, message = "과정명은 최소 1글자 이상, 최대 200자 이하여야 합니다.")
    @ApiModelProperty(value = "과정명(200자 이하)", example = "한화시스템 BEYOND SW 캠프", required = true)
    private String courseName;

    @NotNull(message = "평점은 필수 입력 항목입니다.")
    @Min(value = 1, message = "평점은 최소 1 이상이어야 합니다.")
    @Max(value = 5, message = "평점은 최대 5 이하여야 합니다.")
    @ApiModelProperty(value = "평점", example = "1", required = true)
    private Integer courseEvaluation;
}
