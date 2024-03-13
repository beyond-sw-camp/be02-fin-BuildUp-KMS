package com.example.bootshelf.boardsvc.board.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class PostCreateBoardReq {

    @NotBlank(message = "제목은 필수 입력 사항입니다.")
    @Size(max = 200, message = "100자 미만으로 입력해주세요")
    private String title;

    @NotBlank(message = "내용은 반드시 입력해주세요.")
    @Size(max = 400, message = "400자 미만으로 입력해주세요")
    private String content;

    private Integer boardCategoryIdx;

    private List<String> tagList;
}
