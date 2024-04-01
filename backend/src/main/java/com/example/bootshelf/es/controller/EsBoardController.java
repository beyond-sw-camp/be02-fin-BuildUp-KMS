package com.example.bootshelf.es.controller;

import com.example.bootshelf.boardsvc.board.model.response.GetBoardListByQueryRes;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.es.model.EsBoard;
import com.example.bootshelf.es.service.EsBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/es/board")
public class EsBoardController {
    private final EsBoardService esBoardService;

    // 제목으로 검색
    @GetMapping("/search")
    @ResponseBody
    public SearchHits<EsBoard> search(
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esBoardService.searchWordByElastic(title, pageable);
    }

    // 제목+내용 검색
    @GetMapping("/search/content")
    @ResponseBody
    public SearchHits<EsBoard> titleContentSearch(
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esBoardService.titleContentSearchByElastic(title, pageable);
    }
}
