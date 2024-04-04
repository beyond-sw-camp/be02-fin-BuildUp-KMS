package com.example.bootshelf.esboard.repository;

import com.example.bootshelf.esboard.model.entity.EsBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
public interface EsBoardRepository extends ElasticsearchRepository<EsBoard, String> {
//    Page<EsBoard> findByBoardTitleContaining(String title, Pageable pageable);
//
//
//    @Query("{\"bool\": {\"filter\": [{\"term\": {\"status\": \"true\"}}, {\"term\": {\"boardCategory\": :#{#boardCategory}}} ]}}")
//    Page<EsBoard> findByBoardTitleAndBoardCategory(Integer categoryIdx, String title, Pageable pageable);
}
