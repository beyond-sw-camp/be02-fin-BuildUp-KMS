package com.example.bootshelf.esReview.service;

import com.example.bootshelf.es.model.entity.EsBoard;
import com.example.bootshelf.esReview.model.entity.EsReview;
import com.example.bootshelf.esReview.repository.EsReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class EsReviewService {

    private final EsReviewRepository esReviewRepository;


    // 제목으로 검색(메인)
    public SearchHits<EsReview> titleSearchByMain(@NotNull String title, Pageable pageable) {
        SearchHits<EsReview> searchHits = esReviewRepository.titleSearchByMain(title, pageable);
        return searchHits;
    }

    // 제목+내용으로 검색(메인)
    public SearchHits<EsReview> titleContentSearchByMain(@NotNull String title, Pageable pageable) {
        SearchHits<EsReview> searchHits = esReviewRepository.titleContentSearchByMain(title, pageable);
        return searchHits;
    }

    // 제목+내용으로 검색(과정후기)
    public SearchHits<EsReview> titleContentSearchByCourse(@NotNull Integer sortType, String title, Pageable pageable) {
        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열

        if (sortType >= 1 && sortType <= fields.length) {
            String sortField = fields[sortType - 1];

            SearchHits<EsReview> searchHits = esReviewRepository.titleContentSearchByCourse(sortField, title, pageable);
            return searchHits;
        }
        else return null;
    }

    // 제목+내용으로 검색(강사후기)
    public SearchHits<EsReview> titleContentSearchByTeacher(@NotNull Integer sortType, String title, Pageable pageable) {
        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열

        if (sortType >= 1 && sortType <= fields.length) {
            String sortField = fields[sortType - 1];

            SearchHits<EsReview> searchHits = esReviewRepository.titleContentSearchByTeacher(sortField, title, pageable);
            return searchHits;
        }
        else return null;
    }
}
