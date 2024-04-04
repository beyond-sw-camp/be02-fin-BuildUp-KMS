package com.example.bootshelf.boardsvc.boardup.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCheckBoardUpRes {
    private Integer boardUpIdx;
    private Boolean status;
}