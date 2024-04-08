package com.example.bootshelf.esreview.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.esboard.model.response.BoardSearchResResult;
import com.example.bootshelf.esreview.model.entity.EsReview;
import com.example.bootshelf.esreview.model.response.ReviewSearchRes;
import com.example.bootshelf.esreview.model.response.ReviewSearchResResult;
import com.example.bootshelf.esreview.repository.EsReviewRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EsReviewService {

    private final EsReviewRepository esReviewRepository;

    // 메인 검색(통합)
    public BaseRes titleSearchByMain(@NotNull Integer selectedDropdownValue, String title, Pageable pageable) {

        SearchHits<EsReview> searchHits = esReviewRepository.titleSearchByMain(selectedDropdownValue, title, pageable);

        List<EsReview> searchContent = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        List<ReviewSearchRes> reviewSearchRes = new ArrayList<>();

        for (EsReview result : searchContent) {
            ReviewSearchRes response = ReviewSearchRes.builder()
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

            Document doc = Jsoup.parse(result.getReviewContent());
            Elements images = doc.select("img");

            if (!images.isEmpty()) {
                response.setReviewImage(images.get(0).attr("src"));
            }

            reviewSearchRes.add(response);
        }

        ReviewSearchResResult result = ReviewSearchResResult.builder()
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

    // 목록 조회 시 글만 추출
    public static String extractText(String html) {
        Document doc = Jsoup.parse(html);
        return doc.text();
    }

    // 제목+내용+정렬 검색 (통합)
    public BaseRes titleContentSearch(@NotNull Integer categoryIdx, Integer sortType, String title, Pageable pageable) {
        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열

        if (sortType >= 1 && sortType <= fields.length) {
            String sortField = fields[sortType - 1];

            SearchHits<EsReview> searchHits = esReviewRepository.titleContentSearch(categoryIdx, sortField, title, pageable);

            // EsOperation의 검색 결과를 가져온다. map(SearchHit 객체에서 getContent() 호출 후 해당 문서의 내용인 EsBoard 객체를 추출).collect(추출된 EsBoard 객체들를 List로)
            List<EsReview> searchContent = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
            List<ReviewSearchRes> reviewSearchRes = new ArrayList<>();

            for (EsReview result : searchContent) {
                String textContent = extractText(result.getReviewContent());

                ReviewSearchRes response = ReviewSearchRes.builder()
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
                    response.setReviewImage(images.get(0).attr("src"));
                }

                reviewSearchRes.add(response);
            }

            ReviewSearchResResult result = ReviewSearchResResult.builder()
                    .totalHits(searchHits.getTotalHits())
                    .list(reviewSearchRes)
                    .build();

            BaseRes baseRes = BaseRes.builder()
                    .isSuccess(true)
                    .message("ES 후기 categoryIdx = " + categoryIdx + " 검색 성공")
                    .result(result)
                    .build();

            return baseRes;
        }
        return null;
    }


    /**
     * 아래부터 search after 적용 버전
     */
    // 메인 검색(통합)
    public BaseRes titleSearchByMain2(Integer selectedDropdownValue, String title, int size, List<Object> searchAfter) {
        SearchHits<EsReview> searchHits = esReviewRepository.titleSearchByMain2(selectedDropdownValue, title, size, searchAfter);

        List<EsReview> searchContent = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        List<ReviewSearchRes> reviewSearchRes = new ArrayList<>();

        for (EsReview result : searchContent) {
            ReviewSearchRes response = ReviewSearchRes.builder()
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

            Document doc = Jsoup.parse(result.getReviewContent());
            Elements images = doc.select("img");

            if (!images.isEmpty()) {
                response.setReviewImage(images.get(0).attr("src"));
            }

            reviewSearchRes.add(response);
        }

        Object[] lastSearchAfter = searchHits.getSearchHits().size() > 0
                ? searchHits.getSearchHits().get(searchHits.getSearchHits().size() - 1).getSortValues().toArray(new Object[0])
                : null;

        long totalHits = searchHits.getTotalHits();
        int totalPages = (int) Math.ceil((double) totalHits / size);

        ReviewSearchResResult reviewSearchResResult = ReviewSearchResResult.builder()
                .totalHits(totalHits)
                .totalPages(totalPages)
                .list(reviewSearchRes)
                .lastSearchAfter(lastSearchAfter)
                .build();


        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("ES 메인 제목 검색 성공")
                .result(reviewSearchResResult)
                .build();
        return baseRes;

    }

    // 제목+내용+정렬 검색 (통합)
    public BaseRes titleContentSearch2(Integer categoryIdx, Integer sortType, String title, int size, List<Object> searchAfter) {
        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열

        if (sortType >= 1 && sortType <= fields.length) {
            String sortField = fields[sortType - 1];

            SearchHits<EsReview> searchHits = esReviewRepository.titleContentSearch2(categoryIdx, sortField, title, size, searchAfter);

            // EsOperation의 검색 결과를 가져온다. map(SearchHit 객체에서 getContent() 호출 후 해당 문서의 내용인 EsBoard 객체를 추출).collect(추출된 EsBoard 객체들를 List로)
            List<EsReview> searchContent = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
            List<ReviewSearchRes> reviewSearchRes = new ArrayList<>();

            for (EsReview result : searchContent) {
                String textContent = extractText(result.getReviewContent());

                ReviewSearchRes response = ReviewSearchRes.builder()
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
                    response.setReviewImage(images.get(0).attr("src"));
                }

                reviewSearchRes.add(response);
            }

            Object[] lastSearchAfter = searchHits.getSearchHits().size() > 0
                    ? searchHits.getSearchHits().get(searchHits.getSearchHits().size() - 1).getSortValues().toArray(new Object[0])
                    : null;

            long totalHits = searchHits.getTotalHits();
            int totalPages = (int) Math.ceil((double) totalHits / size);

            ReviewSearchResResult reviewSearchResResult = ReviewSearchResResult.builder()
                    .totalHits(totalHits)
                    .totalPages(totalPages)
                    .list(reviewSearchRes)
                    .lastSearchAfter(lastSearchAfter)
                    .build();

            BaseRes baseRes = BaseRes.builder()
                    .isSuccess(true)
                    .message("ES 후기 categoryIdx = " + categoryIdx + " 검색 성공")
                    .result(reviewSearchResResult)
                    .build();

            return baseRes;
        }
        return null;
    }
}
