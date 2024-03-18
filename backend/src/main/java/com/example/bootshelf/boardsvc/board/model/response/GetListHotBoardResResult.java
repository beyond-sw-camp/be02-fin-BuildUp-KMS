package com.example.bootshelf.boardsvc.board.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListHotBoardResResult {
    private Long totalCnt;
    private Integer totalPages;
    private List<GetListHotBoardRes> list;
}
