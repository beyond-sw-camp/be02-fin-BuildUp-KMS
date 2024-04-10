package com.example.bootshelf.application.port.output;

import com.example.bootshelf.adapter.output.es.entity.EsBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public interface GetListBoardPort {

    SearchHits<EsBoard> titleContentSearch(Integer categoryIdx, String sortField, String title, Pageable pageable);
    SearchHits<EsBoard> searchAfterBoard(Integer categoryIdx, String sortField, String title, Integer size, List<Object> searchAfter);
}
