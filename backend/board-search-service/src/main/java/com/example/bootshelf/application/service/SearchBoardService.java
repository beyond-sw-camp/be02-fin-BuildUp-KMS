package com.example.bootshelf.application.service;

import com.example.bootshelf.adapter.output.es.entity.EsBoard;
import com.example.bootshelf.adapter.output.es.response.BoardSearchRes;
import com.example.bootshelf.application.port.input.SearchBoardUseCase;
import com.example.bootshelf.application.port.output.GetListBoardPort;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.UseCase;
import com.example.bootshelf.domain.Board;
import com.example.bootshelf.domain.BoardResult;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.data.domain.Pageable;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class SearchBoardService implements SearchBoardUseCase {

    private final GetListBoardPort getListBoardPort;


    public static String extractText(String html) {
        Document doc = Jsoup.parse(html);
        return doc.text();
    }

    // 제목+내용+정렬 검색 (통합)
    @Override
    public BaseRes searchBoard(Integer categoryIdx, Integer sortType, String title, Pageable pageable) {
        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"}; // 필드 이름 배열

        if (sortType >= 1 && sortType <= fields.length) {
            String sortField = fields[sortType - 1];

            SearchHits<EsBoard> searchHits = getListBoardPort.titleContentSearch(categoryIdx, sortField, title, pageable);

            // EsOperation의 검색 결과를 가져온다. map(SearchHit 객체에서 getContent() 호출 후 해당 문서의 내용인 EsBoard 객체를 추출).collect(추출된 EsBoard 객체들를 List로)
            List<EsBoard> searchContent = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
            List<BoardSearchRes> boardSearchRes = new ArrayList<>();

            for (EsBoard result : searchContent) {

                String textContent = extractText(result.getBoardContent());

                BoardSearchRes response = BoardSearchRes.builder()
                        .idx(Integer.valueOf(result.getId()))
                        .boardCategory(result.getBoardcategory_idx())
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
                        .tags(result.getTags())
                        .build();

                Document doc = Jsoup.parse(result.getBoardContent());
                Elements images = doc.select("img");

                if (!images.isEmpty()) {
                    response.setBoardImage(images.get(0).attr("src"));
                }

                boardSearchRes.add(response);
            }
            BoardResult result = BoardResult.builder()
                    .totalHits(searchHits.getTotalHits())
                    .list(boardSearchRes)
                    .build();

            BaseRes baseRes = BaseRes.builder()
                    .isSuccess(true)
                    .message("ES 게시판 categoryIdx=" + categoryIdx + " 검색 성공")
                    .result(result)
                    .build();

            return baseRes;
        }
        return null;
    }

    @Override
    public BaseRes searchAfterBoard(Integer categoryIdx, Integer sortType, String title, int size, List<Object> searchAfterStr) {
        String[] fields = {"createdAt", "upCnt", "viewCnt", "scrapCnt", "commentCnt"};

        if (sortType >= 1 && sortType <= fields.length) {
            String sortField = fields[sortType - 1];

            SearchHits<EsBoard> searchHits = getListBoardPort.searchAfterBoard(categoryIdx, sortField, title, size, searchAfterStr);

            List<EsBoard> searchContent = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
            List<BoardSearchRes> boardSearchRes = new ArrayList<>();

            for (EsBoard result : searchContent) {
                String textContent = extractText(result.getBoardContent());

                BoardSearchRes response = BoardSearchRes.builder()
                        .idx(Integer.valueOf(result.getId()))
                        .boardCategory(result.getBoardcategory_idx())
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
                        .tags(result.getTags())
                        .build();

                Document doc = Jsoup.parse(result.getBoardContent());
                Elements images = doc.select("img");

                if (!images.isEmpty()) {
                    response.setBoardImage(images.get(0).attr("src"));
                }

                boardSearchRes.add(response);
            }

            Object[] lastSearchAfter = searchHits.getSearchHits().size() > 0
                    ? searchHits.getSearchHits().get(searchHits.getSearchHits().size() - 1).getSortValues().toArray(new Object[0])
                    : null;

            long totalHits = searchHits.getTotalHits();

            BoardResult boardResult = BoardResult.builder()
                    .totalHits(totalHits)
                    .list(boardSearchRes)
                    .lastSearchAfter(lastSearchAfter)
                    .build();

            BaseRes baseRes = BaseRes.builder()
                    .isSuccess(true)
                    .message("ES 게시판 categoryIdx = " + categoryIdx + " 검색 성공")
                    .result(boardResult)
                    .build();

            return baseRes;
        }
        return null;
    }

}

