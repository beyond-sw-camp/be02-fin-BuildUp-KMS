package com.example.bootshelf.boardup.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetFindBoardUpRes {
    private Integer boardUpIdx;
    private Integer userIdx;
    private Integer boardIdx;
}