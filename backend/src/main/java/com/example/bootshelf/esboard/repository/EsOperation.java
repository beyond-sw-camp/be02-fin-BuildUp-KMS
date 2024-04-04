package com.example.bootshelf.esboard.repository;

import com.example.bootshelf.esboard.model.entity.EsBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
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
//
//    // 제목+내용 검색(QnA)
//    public SearchHits<EsBoard> titleContentSearchByQnA(String title, Pageable pageable) {
//
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
//                "boardTitle", "boardContent");
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
//                .filter(QueryBuilders.termQuery("status", "true"))
//                .filter(QueryBuilders.termQuery("boardCategory", 2));
//
//        NativeSearchQuery build = new NativeSearchQueryBuilder()
//                .withQuery(multiMatchQueryBuilder)
//                .withFilter(boolQueryBuilder)
//                .withPageable(pageable)
//                .build();
//
//        return operations.search(build, EsBoard.class);
//    }
//
//    // 제목+내용 검색(스터디)
//    public SearchHits<EsBoard> titleContentSearchByStudy(String title, Pageable pageable) {
//
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
//                "boardTitle", "boardContent");
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
//                .filter(QueryBuilders.termQuery("status", "true"))
//                .filter(QueryBuilders.termQuery("boardCategory", 3));
//
//        NativeSearchQuery build = new NativeSearchQueryBuilder()
//                .withQuery(multiMatchQueryBuilder)
//                .withFilter(boolQueryBuilder)
//                .withPageable(pageable)
//                .build();
//
//        return operations.search(build, EsBoard.class);
//    }


//    // 제목+내용+정렬 검색(지식공유)
//    public SearchHits<EsBoard> titleContentSearchByKnowledge(String sortField, String title, Pageable pageable) {
//
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
//                "boardTitle", "boardContent");
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
//                .filter(QueryBuilders.termQuery("status", "true"))
//                .filter(QueryBuilders.termQuery("boardCategory", 1));
//
//        NativeSearchQuery build = new NativeSearchQueryBuilder()
//                .withQuery(multiMatchQueryBuilder)
//                .withFilter(boolQueryBuilder)
//                .withPageable(pageable)
//                .withSort(SortBuilders.fieldSort(sortField).order(SortOrder.ASC))
//                .build();
//
//                return operations.search(build, EsBoard.class);
//    }

    // 제목+내용+정렬 검색(QnA)
    public SearchHits<EsBoard> titleContentSearchByQnA(String sortField, String title, Pageable pageable) {

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
                "boardTitle", "boardContent");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("status", "true"))
                .filter(QueryBuilders.termQuery("boardCategory", 3));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder)
                .withFilter(boolQueryBuilder)
                .withPageable(pageable)
                .withSort(SortBuilders.fieldSort(sortField).order(SortOrder.ASC))
                .build();

        return operations.search(build, EsBoard.class);
    }

    // 제목+내용+정렬 검색(스터디)
    public SearchHits<EsBoard> titleContentSearchByStudy(String sortField, String title, Pageable pageable) {

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
                "boardTitle", "boardContent");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("status", "true"))
                .filter(QueryBuilders.termQuery("boardCategory", 2));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder)
                .withFilter(boolQueryBuilder)
                .withPageable(pageable)
                .withSort(SortBuilders.fieldSort(sortField).order(SortOrder.ASC))
                .build();

        return operations.search(build, EsBoard.class);
    }

    // 제목+내용+정렬 검색(인기글)
    public SearchHits<EsBoard> titleContentSearchByHot(String sortField, String title, Pageable pageable) {

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
                "boardTitle", "boardContent");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("status", "true"))
                .filter(QueryBuilders.termQuery("boardCategory", 3));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder)
                .withFilter(boolQueryBuilder)
                .withPageable(pageable)
                .withSort(SortBuilders.fieldSort(sortField).order(SortOrder.ASC))
                .build();

        return operations.search(build, EsBoard.class);
    }

    // 제목+내용+정렬 검색(통합)
    public SearchHits<EsBoard> titleContentSearch(Integer categoryIdx ,String sortField, String title, Pageable pageable) {

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
                "boardTitle", "boardContent");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("status", "true"))
                .filter(QueryBuilders.termQuery("boardCategory", categoryIdx));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder)
                .withFilter(boolQueryBuilder)
                .withPageable(pageable)
                .withSort(SortBuilders.fieldSort(sortField).order(SortOrder.ASC))
                .build();

        return operations.search(build, EsBoard.class);
    }





}
