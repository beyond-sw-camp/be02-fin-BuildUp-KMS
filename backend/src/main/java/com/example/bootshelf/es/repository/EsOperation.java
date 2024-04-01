package com.example.bootshelf.es.repository;

import com.example.bootshelf.es.model.entity.EsBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Getter
public class EsOperation {

    private final ElasticsearchOperations operations;


    // 제목검색
    public SearchHits<EsBoard> keywordSearchByElastic(String title, Pageable pageable) {

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("boardTitle", title);

//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(boardCategory, title,
//                "boardCategory", "boardTitle");

//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("status", "true" );

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("status", "true"))
                .filter(QueryBuilders.termQuery("boardCategory", 1));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(matchQueryBuilder)
                .withFilter(boolQueryBuilder)
                .withPageable(pageable)
                .build();

        return operations.search(build, EsBoard.class);
    }

    // 제목+내용 검색(지식공유)
    public SearchHits<EsBoard> titleContentSearchByKnowledge(String title, Pageable pageable) {

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
                "boardTitle", "boardContent");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("status", "true"))
                .filter(QueryBuilders.termQuery("boardCategory", 1));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder)
                .withFilter(boolQueryBuilder)
                .withPageable(pageable)
                .build();

        return operations.search(build, EsBoard.class);
    }

    // 제목+내용 검색(QnA)
    public SearchHits<EsBoard> titleContentSearchByQnA(String title, Pageable pageable) {

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
                "boardTitle", "boardContent");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("status", "true"))
                .filter(QueryBuilders.termQuery("boardCategory", 2));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder)
                .withFilter(boolQueryBuilder)
                .withPageable(pageable)
                .build();

        return operations.search(build, EsBoard.class);
    }

    // 제목+내용 검색(스터디)
    public SearchHits<EsBoard> titleContentSearchByStudy(String title, Pageable pageable) {

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
                "boardTitle", "boardContent");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("status", "true"))
                .filter(QueryBuilders.termQuery("boardCategory", 3));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder)
                .withFilter(boolQueryBuilder)
                .withPageable(pageable)
                .build();

        return operations.search(build, EsBoard.class);
    }
}
