package com.example.bootshelf.esboard.service;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.esboard.model.entity.EsBoard;
import com.example.bootshelf.esboard.model.response.BoardSearchRes;
import com.example.bootshelf.esboard.model.response.BoardSearchResResult;
import com.example.bootshelf.esboard.repository.EsBoardRepository;
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
public class EsBoardService {
    private final EsBoardRepository esBoardRepository;

    // 제목+내용 검색 (지식공유)
    public SearchHits<EsBoard> titleContentSearchByKnowledge(@NotNull String title, Pageable pageable) {

            SearchHits<EsBoard> searchHits = esBoardRepository.titleContentSearchByKnowledge(title, pageable);
            return searchHits;
    }

    // 제목+내용+정렬 검색 (QnA)
    public SearchHits<EsBoard> titleContentSearchByQnA(@NotNull Integer sortType, String title, Pageable pageable) {
        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열

        if (sortType >= 1 && sortType <= fields.length) {
            String sortField = fields[sortType - 1];

            SearchHits<EsBoard> searchHits = esBoardRepository.titleContentSearchByQnA(sortField, title, pageable);
            return searchHits;
        }
        else return null;
    }

    // 제목+내용+정렬 검색 (스터디)
    public SearchHits<EsBoard> titleContentSearchByStudy(@NotNull Integer sortType, String title, Pageable pageable) {
        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열

        if (sortType >= 1 && sortType <= fields.length) {
            String sortField = fields[sortType - 1];

            SearchHits<EsBoard> searchHits = esBoardRepository.titleContentSearchByStudy(sortField, title, pageable);
            return searchHits;
        }
        else return null;
    }

    // 제목+내용+정렬 검색 (인기글)
    public SearchHits<EsBoard> titleContentSearchByHot(@NotNull Integer sortType, String title, Pageable pageable) {
        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 정렬 순서 배열

        if (sortType >= 1 && sortType <= fields.length) {
            String sortField = fields[sortType - 1];

            SearchHits<EsBoard> searchHits = esBoardRepository.titleContentSearchByHot(sortField, title, pageable);
            return searchHits;
        }
        else return null;
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

            SearchHits<EsBoard> searchHits = esBoardRepository.titleContentSearch(categoryIdx, sortField, title, pageable);

            // EsOperation의 검색 결과를 가져온다. map(SearchHit 객체에서 getContent() 호출 후 해당 문서의 내용인 EsBoard 객체를 추출).collect(추출된 EsBoard 객체들를 List로)
            List<EsBoard> searchContent = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
            List<BoardSearchRes> boardSearchRes = new ArrayList<>();

            for (EsBoard result : searchContent) {

                String textContent = extractText(result.getBoardContent());

                BoardSearchRes response = BoardSearchRes.builder()
                        .idx(Integer.valueOf(result.getId()))
                        .boardCategory(result.getBoardCategory())
                        .boardTitle(result.getBoardTitle())
                        .boardContent(textContent)
                        .viewCnt(result.getViewCnt())
                        .upCnt(result.getUpCnt())
                        .scrapCnt(result.getScrapCnt())
                        .commentCnt(result.getCommentCnt())
                        .createdAt(result.getCreatedAt())
                        .updatedAt(result.getUpdatedAt())
                        .profileImage(result.getProfileImage())
                        .nickName(result.getNickName())
                        .boardImage(result.getBoardImage())
                        .build();

                Document doc = Jsoup.parse(result.getBoardContent());
                Elements images = doc.select("img");

                if (!images.isEmpty()) {
                    response.setBoardImage(images.get(0).attr("src"));
                }

                boardSearchRes.add(response);
            }
            BoardSearchResResult result = BoardSearchResResult.builder()
                    .totalHits(searchHits.getTotalHits())
                    //.totalPages() 페이지 추가하기..
                    .list(boardSearchRes)
                    .build();

            BaseRes baseRes = BaseRes.builder()
                    .isSuccess(true)
                    .message("ES 게시판 categoryIdx = " + categoryIdx + " 검색 성공")
                    .result(result)
                    .build();

            return baseRes;
        }
        return null;
    }
}