package com.example.bootshelf.application.service;

import com.example.bootshelf.adapter.output.es.entity.EsReview;
import com.example.bootshelf.application.port.input.SearchReviewUseCase;
import com.example.bootshelf.application.port.input.SearchTotalReviewUseCase;
import com.example.bootshelf.application.port.output.GetListReviewPort;
import com.example.bootshelf.application.port.output.GetTotalListReviewPort;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.UseCase;
import com.example.bootshelf.domain.Review;
import com.example.bootshelf.domain.ReviewResult;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class SearchReviewService implements SearchReviewUseCase, SearchTotalReviewUseCase {

    private final GetListReviewPort getListReviewPort;
    private final GetTotalListReviewPort getTotalListReviewPort;

    public static String extractText(String html) {
        Document doc = Jsoup.parse(html);
        return doc.text();
    }

    @Override
    public BaseRes searchTotalReview(Integer selectedDropdownValue, String title, Pageable pageable) {

        SearchHits<EsReview> searchHits = getTotalListReviewPort.titleSearchByMain(selectedDropdownValue, title, pageable);

        List<EsReview> searchContent = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        List<Review> reviewSearchRes = new ArrayList<>();

        for (EsReview result : searchContent) {
            Review response = Review.builder()
                    .idx(Integer.valueOf(result.getId()))
                    .reviewCategory(result.getReviewCategory())
                    .reviewTitle(result.getReviewTitle())
                    .reviewContent(result.getReviewContent())
                    .courseName(result.getCourseName())
                    .courseEvaluation(result.getCourseEvaluation())
                    .viewCnt(result.getViewCnt())
                    .upCnt(result.getUpCnt())
                    .scrapCnt(result.getScrapCnt())
                    .commentCnt(result.getCommentCnt())
                    .createdAt(result.getCreatedAt())
                    .updatedAt(result.getUpdatedAt())
                    .nickName(result.getNickName())
                    .profileImage(result.getProfileImage())
                    .build();

            reviewSearchRes.add(response);
        }
        ReviewResult result = ReviewResult.builder()
                .totalHits(searchHits.getTotalHits())
                //.totalPages() 페이지 추가하기..
                .list(reviewSearchRes)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("ES 메인 제목 검색 성공")
                .result(result)
                .build();

        return baseRes;

    }

    @Override
    public BaseRes searchReview(Integer categoryIdx, Integer sortType, String title, Pageable pageable) {

        // 목록 조회 시 글만 추출
        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"};

        if (sortType >= 1 && sortType <= fields.length) {
            String sortField = fields[sortType - 1];

            SearchHits<EsReview> searchHits = getListReviewPort.titleContentSearch(categoryIdx, sortField, title, pageable);

            // EsOperation의 검색 결과를 가져온다. map(SearchHit 객체에서 getContent() 호출 후 해당 문서의 내용인 EsBoard 객체를 추출).collect(추출된 EsBoard 객체들를 List로)
            List<EsReview> searchContent = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());

            List<Review> reviewSearchRes = new ArrayList<>();

            for (EsReview result : searchContent) {
                String textContent = extractText(result.getReviewContent());

                Review response = Review.builder()
                        .idx(Integer.valueOf(result.getId()))
                        .reviewCategory(result.getReviewCategory())
                        .reviewTitle(result.getReviewTitle())
                        .reviewContent(textContent)
                        .courseName(result.getCourseName())
                        .courseEvaluation(result.getCourseEvaluation())
                        .viewCnt(result.getViewCnt())
                        .upCnt(result.getUpCnt())
                        .scrapCnt(result.getScrapCnt())
                        .commentCnt(result.getCommentCnt())
                        .createdAt(result.getCreatedAt())
                        .updatedAt(result.getUpdatedAt())
                        .nickName(result.getNickName())
                        .profileImage(result.getProfileImage())
                        .build();

                Document doc = Jsoup.parse(result.getReviewContent());
                Elements images = doc.select("img");

                if (!images.isEmpty()) {
                    response.setReviewImg(images.get(0).attr("src"));
                }

                reviewSearchRes.add(response);
            }

            ReviewResult result = ReviewResult.builder()
                    .totalHits(searchHits.getTotalHits())
                    //.totalPages() 페이지 추가하기..
                    .list(reviewSearchRes)
                    .build();

            BaseRes baseRes = BaseRes.builder()
                    .isSuccess(true)
                    .message("ES 후기 categoryIdx = " + categoryIdx + " 검색 성공")
                    .result(result)
                    .build();

            return baseRes;
        } else {
            return null;
        }
    }
}
