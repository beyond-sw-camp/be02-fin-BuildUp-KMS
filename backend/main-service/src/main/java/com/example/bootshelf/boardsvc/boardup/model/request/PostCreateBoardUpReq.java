package com.example.bootshelf.boardsvc.boardup.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateBoardUpReq {

    @NotNull
    @Positive
    private Integer boardIdx;
}
