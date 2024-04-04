package com.example.bootshelf.adapter.output.es;

import com.example.bootshelf.adapter.output.es.entity.EsReview;
import com.example.bootshelf.application.port.output.GetListReviewPort;
import com.example.bootshelf.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ExternalSystemAdapter
public class EsReviewAdapter implements GetListReviewPort {

    private final ElasticsearchOperations operations;
    @Override
    public SearchHits<EsReview> titleContentSearch(Integer categoryIdx, String sortField, String title, Pageable pageable) {

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
                "reviewTitle", "reviewContent");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("status", "true"))
                .filter(QueryBuilders.termQuery("reviewCategory", categoryIdx));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder)
                .withFilter(boolQueryBuilder)
                .withPageable(pageable)
                .withSort(SortBuilders.fieldSort(sortField).order(SortOrder.ASC))
                .build();

        return operations.search(build, EsReview.class);
    }
}
