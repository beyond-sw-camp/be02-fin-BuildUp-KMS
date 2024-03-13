package com.example.bootshelf.boardsvc.boardup.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateBoardUpReq {
    private Integer boardIdx;
}
