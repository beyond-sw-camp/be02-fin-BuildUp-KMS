package com.example.bootshelf.domain;

import com.example.bootshelf.adapter.output.es.entity.EsBoard;
import com.example.bootshelf.adapter.output.es.response.BoardSearchRes;
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
    private Object[] lastSearchAfter;
    private List<BoardSearchRes> list;
}
