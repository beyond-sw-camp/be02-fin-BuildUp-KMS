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
import org.springframework.data.domain.PageRequest;
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

                String textContent = extractText(result.getBoardcontent());

                BoardSearchRes response = BoardSearchRes.builder()
                        .idx(Integer.valueOf(result.getId()))
                        .boardCategory(result.getBoardcategory_idx())
                        .boardTitle(result.getBoardtitle())
                        .boardContent(textContent)
                        .viewCnt(result.getViewcnt())
                        .upCnt(result.getUpcnt())
                        .scrapCnt(result.getScrapcnt())
                        .commentCnt(result.getCommentcnt())
                        .createdAt(result.getCreatedAt())
                        .updatedAt(result.getUpdatedat())
                        .profileImage(result.getProfileimage())
                        .nickName(result.getNickname())
                        .boardImage(result.getBoardImage())
                        .tags(result.getTags())
                        .build();

                Document doc = Jsoup.parse(result.getBoardcontent());
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


    // search after ver2
    public BaseRes titleContentSearch2(Integer categoryIdx, Integer sortType, String title, int size, List<Object> searchAfter) {
        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"};
        String sortField = sortType >= 1 && sortType <= fields.length ? fields[sortType - 1] : fields[0];

        SearchHits<EsBoard> searchHits = esBoardRepository.titleContentSearch2(categoryIdx, sortField, title, size, searchAfter);

        List<EsBoard> searchContent = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());

        List<BoardSearchRes> boardSearchRes = new ArrayList<>();

        for (EsBoard result : searchContent) {

            String textContent = extractText(result.getBoardcontent());

            BoardSearchRes response = BoardSearchRes.builder()
                    .idx(Integer.valueOf(result.getId()))
                    .boardCategory(result.getBoardcategory_idx())
                    .boardTitle(result.getBoardtitle())
                    .boardContent(textContent)
                    .viewCnt(result.getViewcnt())
                    .upCnt(result.getUpcnt())
                    .scrapCnt(result.getScrapcnt())
                    .commentCnt(result.getCommentcnt())
                    .createdAt(result.getCreatedAt())
                    .updatedAt(result.getUpdatedat())
                    .profileImage(result.getProfileimage())
                    .nickName(result.getNickname())
                    .boardImage(result.getBoardImage())
                    .tags(result.getTags())
                    .build();

            Document doc = Jsoup.parse(result.getBoardcontent());
            Elements images = doc.select("img");

            if (!images.isEmpty()) {
                response.setBoardImage(images.get(0).attr("src"));
            }

            boardSearchRes.add(response);
        }

        // 기존의 코드
        Object[] lastSearchAfter = searchHits.getSearchHits().size() > 0
                ? searchHits.getSearchHits().get(searchHits.getSearchHits().size() - 1).getSortValues().toArray(new Object[0])
                : null;

        long totalHits = searchHits.getTotalHits();
        int totalPages = (int) Math.ceil((double) totalHits / size);

        BoardSearchResResult boardSearchResResult = BoardSearchResResult.builder()
                .totalHits(totalHits)
                .totalPages(totalPages)
                .list(boardSearchRes)
                .lastSearchAfter(lastSearchAfter)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("ES 게시판 categoryIdx = " + categoryIdx + " 검색 성공")
                .result(boardSearchResResult)
                .build();

        return baseRes;
    }
}