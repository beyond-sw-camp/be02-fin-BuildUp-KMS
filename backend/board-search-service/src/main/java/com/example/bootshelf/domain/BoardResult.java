package com.example.bootshelf.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class BoardResult {

    private Long totalHits;
    private Integer totalPages;
    private List<Board> list;

}
