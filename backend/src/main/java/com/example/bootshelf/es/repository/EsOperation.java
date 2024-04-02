package com.example.bootshelf.es.repository;

//import com.example.bootshelf.es.model.EsBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

//@Repository
//@RequiredArgsConstructor
//@Getter
//public class EsOperation {
//
//    private final ElasticsearchOperations operations;
//
//
//    // 제목 검색
//    public SearchHits<EsBoard> keywordSearchByElastic(String title, Pageable pageable) {
//
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("boardTitle", title);
//
//        NativeSearchQuery build = new NativeSearchQueryBuilder()
//                .withQuery(matchQueryBuilder)
//                .withPageable(pageable)
//                .build();
//
//        return operations.search(build, EsBoard.class);
//    }
//
//    // 제목+내용 검색
//    public SearchHits<EsBoard> titleContentSearchByElastic(String title, Pageable pageable) {
//
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
//                "boardTitle", "boardContent");
//
//        NativeSearchQuery build = new NativeSearchQueryBuilder()
//                .withQuery(multiMatchQueryBuilder)
//                .withPageable(pageable)
//                .build();
//
//        return operations.search(build, EsBoard.class);
//    }
//}
