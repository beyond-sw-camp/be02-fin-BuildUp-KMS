package com.example.bootshelf.esboard.repository;

import com.example.bootshelf.esboard.model.entity.EsBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
@Getter
public class EsBoardRepository {

    private final ElasticsearchOperations operations;

    // 제목+내용+정렬 검색(통합)
    public SearchHits<EsBoard> titleContentSearch(Integer categoryIdx, String sortField, String title, Pageable pageable) {

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title,
                "boardtitle", "boardcontent");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("boardcategory_idx", categoryIdx));

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("boardtitle"); // 하이라이팅을 적용할 필드 지정
        highlightBuilder.field("boardcontent"); // 하이라이팅을 적용할 필드 지정
        highlightBuilder.preTags("<em>"); // 하이라이트 시작 태그
        highlightBuilder.postTags("</em>"); // 하이라이트 종료 태그


        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder)
                .withFilter(boolQueryBuilder)
                .withHighlightBuilder(highlightBuilder) // 하이라이트 설정 추가
                .withPageable(pageable)
                .withSort(SortBuilders.fieldSort(sortField).order(SortOrder.ASC))
                .build();

        return operations.search(build, EsBoard.class);
    }

    // search after 적용 ver
    public SearchHits<EsBoard> titleContentSearch2(Integer categoryIdx, String sortField, String title, int size, List<Object> searchAfter) {
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(title, "boardtitle", "boardcontent");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.termQuery("boardcategory_idx", categoryIdx));

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder)
                .withFilter(boolQueryBuilder)
                .withSort(SortBuilders.fieldSort(sortField).order(SortOrder.ASC))
                .withPageable(PageRequest.of(0, size));

        if (searchAfter != null && !searchAfter.isEmpty()) {
            queryBuilder.withSort(SortBuilders.fieldSort("_id").order(SortOrder.ASC)); // Additional sort to ensure a stable order
            queryBuilder.withSearchAfter(searchAfter);
        }

        Query searchQuery = queryBuilder.build();
        return operations.search(searchQuery, EsBoard.class);
    }

}
