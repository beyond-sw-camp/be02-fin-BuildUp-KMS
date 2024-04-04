//package com.example.bootshelf.esreview.service;
//
//import com.example.bootshelf.common.BaseRes;
//import com.example.bootshelf.esreview.model.entity.EsReview;
//import com.example.bootshelf.esreview.model.response.ReviewSearchRes;
//import com.example.bootshelf.esreview.repository.EsReviewRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.SearchHit;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.stereotype.Service;
//
//import javax.validation.constraints.NotNull;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class EsReviewService {
//
//    private final EsReviewRepository esReviewRepository;
//
//
//    // 제목으로 검색(메인)
//    public BaseRes titleSearchByMain(@NotNull String title, Pageable pageable) {
//        SearchHits<EsReview> searchHits = esReviewRepository.titleSearchByMain(title, pageable);
//
//        List<EsReview> searchContent = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
//        List<ReviewSearchRes> reviewSearchRes = new ArrayList<>();
//
//        for (EsReview result : searchContent) {
//            ReviewSearchRes response = ReviewSearchRes.builder()
//                    .idx(Integer.valueOf(result.getId()))
//                    .user(result.getUser())
//                    .reviewCategory(result.getReviewCategory())
//                    .reviewTitle(result.getReviewTitle())
//                    .reviewContent(result.getReviewContent())
//                    .courseName(result.getCourseName())
//                    .courseEvaluation(result.getCourseEvaluation())
//                    .viewCnt(result.getViewCnt())
//                    .upCnt(result.getUpCnt())
//                    .scrapCnt(result.getScrapCnt())
//                    .commentCnt(result.getCommentCnt())
//                    .status(result.getStatus())
//                    .createdAt(result.getCreatedAt())
//                    .updatedAt(result.getUpdatedAt())
//                    .totalHits(searchHits.getTotalHits())
//                    .build();
//
//            reviewSearchRes.add(response);
//        }
//
//        BaseRes baseRes = BaseRes.builder()
//                .isSuccess(true)
//                .message("ES 메인 제목 검색 성공")
//                .result(reviewSearchRes)
//                .build();
//
//        return baseRes;
//    }
//
//    // 제목+내용으로 검색(메인)
//    public BaseRes titleContentSearchByMain(@NotNull String title, Pageable pageable) {
//        SearchHits<EsReview> searchHits = esReviewRepository.titleContentSearchByMain(title, pageable);
//
//        List<EsReview> searchContent = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
//        List<ReviewSearchRes> reviewSearchRes = new ArrayList<>();
//
//        for (EsReview result : searchContent) {
//            ReviewSearchRes response = ReviewSearchRes.builder()
//                    .idx(Integer.valueOf(result.getId()))
//                    .user(result.getUser())
//                    .reviewCategory(result.getReviewCategory())
//                    .reviewTitle(result.getReviewTitle())
//                    .reviewContent(result.getReviewContent())
//                    .courseName(result.getCourseName())
//                    .courseEvaluation(result.getCourseEvaluation())
//                    .viewCnt(result.getViewCnt())
//                    .upCnt(result.getUpCnt())
//                    .scrapCnt(result.getScrapCnt())
//                    .commentCnt(result.getCommentCnt())
//                    .status(result.getStatus())
//                    .createdAt(result.getCreatedAt())
//                    .updatedAt(result.getUpdatedAt())
//                    .totalHits(searchHits.getTotalHits())
//                    .build();
//
//            reviewSearchRes.add(response);
//        }
//
//        BaseRes baseRes = BaseRes.builder()
//                .isSuccess(true)
//                .message("ES 메인 제목+내용 검색 성공")
//                .result(reviewSearchRes)
//                .build();
//
//        return baseRes;
//    }
//
//    // 제목+내용으로 검색(과정후기)
//    public SearchHits<EsReview> titleContentSearchByCourse(@NotNull Integer sortType, String title, Pageable pageable) {
//        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열
//
//        if (sortType >= 1 && sortType <= fields.length) {
//            String sortField = fields[sortType - 1];
//
//            SearchHits<EsReview> searchHits = esReviewRepository.titleContentSearchByCourse(sortField, title, pageable);
//            return searchHits;
//        }
//        else return null;
//    }
//
//    // 제목+내용으로 검색(강사후기)
//    public SearchHits<EsReview> titleContentSearchByTeacher(@NotNull Integer sortType, String title, Pageable pageable) {
//        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열
//
//        if (sortType >= 1 && sortType <= fields.length) {
//            String sortField = fields[sortType - 1];
//
//            SearchHits<EsReview> searchHits = esReviewRepository.titleContentSearchByTeacher(sortField, title, pageable);
//            return searchHits;
//        }
//        else return null;
//    }
//
//    // 제목+내용+정렬 검색 (통합)
//    public BaseRes titleContentSearch(@NotNull Integer categoryIdx, Integer sortType, String title, Pageable pageable) {
//        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열
//
//        if (sortType >= 1 && sortType <= fields.length) {
//            String sortField = fields[sortType - 1];
//
//            SearchHits<EsReview> searchHits = esReviewRepository.titleContentSearch(categoryIdx, sortField, title, pageable);
//
//            // EsOperation의 검색 결과를 가져온다. map(SearchHit 객체에서 getContent() 호출 후 해당 문서의 내용인 EsBoard 객체를 추출).collect(추출된 EsBoard 객체들를 List로)
//            List<EsReview> searchContent = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
//            List<ReviewSearchRes> reviewSearchRes = new ArrayList<>();
//
//            for (EsReview result : searchContent) {
//                ReviewSearchRes response = ReviewSearchRes.builder()
//                        .idx(Integer.valueOf(result.getId()))
//                        .user(result.getUser())
//                        .reviewCategory(result.getReviewCategory())
//                        .reviewTitle(result.getReviewTitle())
//                        .reviewContent(result.getReviewContent())
//                        .courseName(result.getCourseName())
//                        .courseEvaluation(result.getCourseEvaluation())
//                        .viewCnt(result.getViewCnt())
//                        .upCnt(result.getUpCnt())
//                        .scrapCnt(result.getScrapCnt())
//                        .commentCnt(result.getCommentCnt())
//                        .status(result.getStatus())
//                        .createdAt(result.getCreatedAt())
//                        .updatedAt(result.getUpdatedAt())
//                        .totalHits(searchHits.getTotalHits())
//                        .build();
//
//                reviewSearchRes.add(response);
//            }
//
//            BaseRes baseRes = BaseRes.builder()
//                    .isSuccess(true)
//                    .message("ES 후기 categoryIdx="+ categoryIdx +"검색 성공")
//                    .result(reviewSearchRes)
//                    .build();
//
//            return baseRes;
//        }
//        return null;
//    }
//}
