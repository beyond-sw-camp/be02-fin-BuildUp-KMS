//package com.example.bootshelf.es.service;
//
//import com.example.bootshelf.es.model.entity.EsBoard;
//import com.example.bootshelf.es.repository.EsBoardRepository;
//import com.example.bootshelf.es.repository.EsOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.stereotype.Service;
//
//import javax.validation.constraints.NotNull;
//
//@Service
//@RequiredArgsConstructor
//public class EsBoardService {
//    private final EsBoardRepository esBoardRepository;
//    private final EsOperation esOperation;
//
//    // 제목으로 검색(메인)
//    public SearchHits<EsBoard> titleSearchByMain(@NotNull String title, Pageable pageable) {
//        SearchHits<EsBoard> searchHits = esOperation.titleSearchByMain(title, pageable);
//        return searchHits;
//    }
//
//    // 제목+내용으로 검색(메인)
//    public SearchHits<EsBoard> titleContentSearchByMain(@NotNull String title, Pageable pageable) {
//        SearchHits<EsBoard> searchHits = esOperation.titleContentSearchByMain(title, pageable);
//        return searchHits;
//    }
//
////    // 제목+내용 검색 (지식공유)
////    public SearchHits<EsBoard> titleContentSearchByKnowledge(@NotNull String title, Pageable pageable) {
////
////            SearchHits<EsBoard> searchHits = esOperation.titleContentSearchByKnowledge(title, pageable);
////            return searchHits;
////    }
////        // 제목+내용 검색 (QaA)
////        public SearchHits<EsBoard> titleContentSearchByQnA(@NotNull String title, Pageable pageable) {
////            SearchHits<EsBoard> searchHits = esOperation.titleContentSearchByQnA(title, pageable);
////            return searchHits;
////        }
////
////        // 제목+내용 검색 (스터디)
////        public SearchHits<EsBoard> titleContentSearchByStudy(@NotNull String title, Pageable pageable) {
////            SearchHits<EsBoard> searchHits = esOperation.titleContentSearchByStudy(title, pageable);
////            return searchHits;
////        }
//
//
//    // 제목+내용+정렬 검색 (지식공유)
//    public SearchHits<EsBoard> titleContentSearchByKnowledge(@NotNull Integer sortType, String title, Pageable pageable) {
//        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열
//
//        if (sortType >= 1 && sortType <= fields.length) {
//            String sortField = fields[sortType - 1];
//
//            SearchHits<EsBoard> searchHits = esOperation.titleContentSearchByKnowledge(sortField, title, pageable);
//            return searchHits;
//        }
//        else return null;
//    }
//
//    // 제목+내용+정렬 검색 (QnA)
//    public SearchHits<EsBoard> titleContentSearchByQnA(@NotNull Integer sortType, String title, Pageable pageable) {
//        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열
//
//        if (sortType >= 1 && sortType <= fields.length) {
//            String sortField = fields[sortType - 1];
//
//            SearchHits<EsBoard> searchHits = esOperation.titleContentSearchByQnA(sortField, title, pageable);
//            return searchHits;
//        }
//        else return null;
//    }
//
//    // 제목+내용+정렬 검색 (스터디)
//    public SearchHits<EsBoard> titleContentSearchByStudy(@NotNull Integer sortType, String title, Pageable pageable) {
//        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열
//
//        if (sortType >= 1 && sortType <= fields.length) {
//            String sortField = fields[sortType - 1];
//
//            SearchHits<EsBoard> searchHits = esOperation.titleContentSearchByStudy(sortField, title, pageable);
//            return searchHits;
//        }
//        else return null;
//    }
//
//    // 제목+내용+정렬 검색 (인기글)
//    public SearchHits<EsBoard> titleContentSearchByHot(@NotNull Integer sortType, String title, Pageable pageable) {
//        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열
//
//        if (sortType >= 1 && sortType <= fields.length) {
//            String sortField = fields[sortType - 1];
//
//            SearchHits<EsBoard> searchHits = esOperation.titleContentSearchByHot(sortField, title, pageable);
//            return searchHits;
//        }
//        else return null;
//    }
//
//
//
//
////    // EsRepository 사용
////    public Page<EsBoard> titleContentSearchByElastic2(@NotNull String title, Pageable pageable) {
////        Page<EsBoard> result = esBoardRepository.findByBoardTitle(title, pageable);
////        return result;
////    }
//}