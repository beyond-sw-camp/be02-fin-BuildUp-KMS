//package com.example.bootshelf.esreview.repository;
//
//import com.example.bootshelf.esreview.model.entity.EsReview;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.MatchQueryBuilder;
//import org.elasticsearch.index.query.MultiMatchQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.sort.SortBuilders;
//import org.elasticsearch.search.sort.SortOrder;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.stereotype.Repository;
//
//@Repository
//@RequiredArgsConstructor
//@Getter
//public class EsReviewRepository {
//    private final ElasticsearchOperations operations;
//
//    // 제목 검색(메인)
//    public SearchHits<EsReview> titleSearchByMain(String title, Pageable pageable) {
//
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("reviewTitle", title);
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
//                .filter(QueryBuilders.termQuery("status", "true"));
//
//        NativeSearchQuery build = new NativeSearchQueryBuilder()
//                .withQuery(matchQueryBuilder)
//                .withFilter(boolQueryBuilder)
//                .withPageable(pageable)
//                .build();
//
//        return operations.search(build, EsReview.class);
//    }
//
//    // 제목+내용 검색(메인)
//    public SearchHits<EsReview> titleContentSearchByMain(String title, Pageable pageable) {
//
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
//                "reviewTitle", "reviewContent");
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
//                .filter(QueryBuilders.termQuery("status", "true"));
//
//        NativeSearchQuery build = new NativeSearchQueryBuilder()
//                .withQuery(multiMatchQueryBuilder)
//                .withFilter(boolQueryBuilder)
//                .withPageable(pageable)
//                .build();
//
//        return operations.search(build, EsReview.class);
//    }
//
//    // 제목+내용+정렬 검색(과정후기)
//    public SearchHits<EsReview> titleContentSearchByCourse(String sortField, String title, Pageable pageable) {
//
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
//                "reviewTitle", "reviewContent");
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
//                .filter(QueryBuilders.termQuery("status", "true"))
//                .filter(QueryBuilders.termQuery("reviewCategory", 1));
//
//        NativeSearchQuery build = new NativeSearchQueryBuilder()
//                .withQuery(multiMatchQueryBuilder)
//                .withFilter(boolQueryBuilder)
//                .withPageable(pageable)
//                .withSort(SortBuilders.fieldSort(sortField).order(SortOrder.ASC))
//                .build();
//
//        return operations.search(build, EsReview.class);
//    }
//
//    // 제목+내용+정렬 검색(강사후기)
//    public SearchHits<EsReview> titleContentSearchByTeacher(String sortField, String title, Pageable pageable) {
//
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
//                "reviewTitle", "reviewContent");
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
//                .filter(QueryBuilders.termQuery("status", "true"))
//                .filter(QueryBuilders.termQuery("reviewCategory", 2));
//
//        NativeSearchQuery build = new NativeSearchQueryBuilder()
//                .withQuery(multiMatchQueryBuilder)
//                .withFilter(boolQueryBuilder)
//                .withPageable(pageable)
//                .withSort(SortBuilders.fieldSort(sortField).order(SortOrder.ASC))
//                .build();
//
//        return operations.search(build, EsReview.class);
//    }
//
//    // 제목+내용+정렬 검색(통합)
//    public SearchHits<EsReview> titleContentSearch(Integer categoryIdx ,String sortField, String title, Pageable pageable) {
//
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
//                "reviewTitle", "reviewContent");
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
//                .filter(QueryBuilders.termQuery("status", "true"))
//                .filter(QueryBuilders.termQuery("reviewCategory", categoryIdx));
//
//        NativeSearchQuery build = new NativeSearchQueryBuilder()
//                .withQuery(multiMatchQueryBuilder)
//                .withFilter(boolQueryBuilder)
//                .withPageable(pageable)
//                .withSort(SortBuilders.fieldSort(sortField).order(SortOrder.ASC))
//                .build();
//
//        return operations.search(build, EsReview.class);
//    }
//
//}
