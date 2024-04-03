package com.example.bootshelf.boardsvc.boardup.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetFindBoardUpRes {
    private Integer boardUpIdx;
    private Integer userIdx;
    private Integer boardIdx;
}