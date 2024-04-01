package com.example.bootshelf.es.service;

import com.example.bootshelf.es.model.entity.EsBoard;
import com.example.bootshelf.es.repository.EsBoardRepository;
import com.example.bootshelf.es.repository.EsOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class EsBoardService {
    private final EsBoardRepository esBoardRepository;
    private final EsOperation esOperation;

    // 제목으로 검색
    public SearchHits<EsBoard> searchWordByElastic(@NotNull String title, Pageable pageable) {
        SearchHits<EsBoard> searchHits = esOperation.keywordSearchByElastic(title, pageable);
        return searchHits;
    }

    // 제목+내용 검색 (지식공유)
    public SearchHits<EsBoard> titleContentSearchByKnowledge(@NotNull String title, Pageable pageable) {
        SearchHits<EsBoard> searchHits = esOperation.titleContentSearchByKnowledge(title, pageable);
        return searchHits;
    }

    // 제목+내용 검색 (QaA)
    public SearchHits<EsBoard> titleContentSearchByQnA(@NotNull String title, Pageable pageable) {
        SearchHits<EsBoard> searchHits = esOperation.titleContentSearchByQnA(title, pageable);
        return searchHits;
    }

    // 제목+내용 검색 (스터디)
    public SearchHits<EsBoard> titleContentSearchByStudy(@NotNull String title, Pageable pageable) {
        SearchHits<EsBoard> searchHits = esOperation.titleContentSearchByStudy(title, pageable);
        return searchHits;
    }

//    // EsRepository 사용
//    public Page<EsBoard> titleContentSearchByElastic2(@NotNull String title, Pageable pageable) {
//        Page<EsBoard> result = esBoardRepository.findByBoardTitle(title, pageable);
//        return result;
//    }
}