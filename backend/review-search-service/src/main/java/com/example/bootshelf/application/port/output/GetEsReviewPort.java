package com.example.bootshelf.application.port.output;

import com.example.bootshelf.adapter.output.es.entity.EsReview;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public interface GetEsReviewPort {
    SearchHits<EsReview> esReviewSearch(Integer selectedDropdownValue, String sortField, String title, int size, List<Object> searchAfter);
}
