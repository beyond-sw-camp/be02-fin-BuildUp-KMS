package com.example.bootshelf.es.controller;

import com.example.bootshelf.es.model.entity.EsBoard;
import com.example.bootshelf.es.service.EsBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/es/board")
public class EsBoardController {
    private final EsBoardService esBoardService;

    // 제목으로 검색(메인)
    @GetMapping("/search/title")
    @ResponseBody
    public SearchHits<EsBoard> titleSearchByMain(
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esBoardService.titleSearchByMain(title, pageable);
    }

    // 제목+내용 (메인)
    @GetMapping("/search/title/content")
    @ResponseBody
    public SearchHits<EsBoard> titleContentSearchByMain(
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esBoardService.titleContentSearchByMain(title, pageable);
    }


    // 제목+내용 (지식공유)
    @GetMapping("/search/knowledge")
    @ResponseBody
    public SearchHits<EsBoard> titleContentSearchByKnowledge(
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esBoardService.titleContentSearchByKnowledge(title, pageable);
    }

    // 제목+내용(QnA)
    @GetMapping("/search/qna")
    @ResponseBody
    public SearchHits<EsBoard> titleContentSearchByQnA(
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esBoardService.titleContentSearchByQnA(title, pageable);
    }

    // 제목+내용(스터디)
    @GetMapping("/search/study")
    @ResponseBody
    public SearchHits<EsBoard> titleContentSearchByStudy(
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esBoardService.titleContentSearchByStudy(title, pageable);
    }

    // 제목+내용(인기글 검색)
    @GetMapping("/search/hottag")
    @ResponseBody
    public SearchHits<EsBoard> titleContentSearchByHotTog(
            Integer tagIdx,
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esBoardService.titleContentSearchByHotTog(tagIdx, title, pageable);
    }


//    // 제목 검색, EsRepository 사용
//    @GetMapping("/search2")
//    @ResponseBody
//    public Page<EsBoard> titleContentSearch2(
//            @RequestParam String title,
//            @PageableDefault(size = 20) Pageable pageable
//    ) {
//        return esBoardService.titleContentSearchByElastic2(title, pageable);
//    }
}
