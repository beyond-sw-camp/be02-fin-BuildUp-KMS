//package com.example.bootshelf.es.repository;
//
//import com.example.bootshelf.es.model.entity.EsBoard;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.annotations.Query;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//public interface EsBoardRepository extends ElasticsearchRepository<EsBoard, String> {
//    Page<EsBoard> findByBoardTitleContaining(String title, Pageable pageable);
//
//    @Query()
//    Page<EsBoard> findByBoardTitle(String title, Pageable pageable);
//}
