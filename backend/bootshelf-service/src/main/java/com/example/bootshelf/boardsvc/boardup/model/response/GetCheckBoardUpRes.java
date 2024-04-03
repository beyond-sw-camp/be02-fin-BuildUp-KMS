package com.example.bootshelf.boardsvc.boardup.model.response;

import com.example.bootshelf.boardsvc.boardup.model.entity.BoardUp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCheckBoardUpRes {
    private Integer boardUpIdx;
    private Boolean status;
}