package com.example.bootshelf.domain;

import com.example.bootshelf.adapter.output.es.entity.EsBoard;
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
    private List<EsBoard> list;
    private Object[] lastSearchAfter;

}
