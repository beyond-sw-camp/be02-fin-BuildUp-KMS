package com.example.bootshelf.boardsvc.board.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSearchListBoardResResult {
    private Long totalCnt;
    private Integer totalPages;
    private List<GetSearchListBoardRes> list;
}
