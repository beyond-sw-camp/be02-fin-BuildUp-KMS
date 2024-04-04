package com.example.bootshelf.boardsvc.boardscrap.model.response;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetFindBoardScrapResResult {
    private Long totalCnt;
    private Integer totalPages;
    private List<GetFindBoardScrapRes> list;
}
