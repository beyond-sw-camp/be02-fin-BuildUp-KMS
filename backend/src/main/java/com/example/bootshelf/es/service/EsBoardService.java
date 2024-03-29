package com.example.bootshelf.es.service;

import com.example.bootshelf.es.model.EsBoard;
import com.example.bootshelf.es.repository.EsBoardRepository;
import com.example.bootshelf.es.repository.EsOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class EsBoardService {
    private final EsBoardRepository esBoardRepository;
    private final EsOperation esOperation;

    public Page<EsBoard> searchByTitle(String title, Pageable pageable) {
        return esBoardRepository.findByBoardTitleContaining(title, pageable);
    }

    public SearchHits<EsBoard> searchWordByElastic(@NotNull String title, Pageable pageable) {
        SearchHits<EsBoard> searchHits = esOperation.keywordSearchByElastic(title, pageable);
        return searchHits;
    }
}