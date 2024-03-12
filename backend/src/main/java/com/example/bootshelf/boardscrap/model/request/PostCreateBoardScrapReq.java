package com.example.bootshelf.boardscrap.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateBoardScrapReq {
    private Integer boardIdx;
}
