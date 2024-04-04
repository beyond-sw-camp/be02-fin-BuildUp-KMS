package com.example.bootshelf.boardsvc.boardscrap.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCheckBoardScrapRes {
    private Integer boardScrapIdx;
    private Boolean status;
}