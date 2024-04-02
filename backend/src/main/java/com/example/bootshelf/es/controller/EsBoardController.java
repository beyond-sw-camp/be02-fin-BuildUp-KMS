package com.example.bootshelf.es.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.es.model.entity.EsBoard;
import com.example.bootshelf.es.service.EsBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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



//    // 제목+내용 (지식공유)
//    @GetMapping("/search/knowledge")
//    @ResponseBody
//    public SearchHits<EsBoard> titleContentSearchByKnowledge(
//            @RequestParam String title,
//            @PageableDefault(size = 20) Pageable pageable
//    ) {
//        return esBoardService.titleContentSearchByKnowledge(title, pageable);
//    }
//
//    // 제목+내용(QnA)
//    @GetMapping("/search/qna")
//    @ResponseBody
//    public SearchHits<EsBoard> titleContentSearchByQnA(
//            @RequestParam String title,
//            @PageableDefault(size = 20) Pageable pageable
//    ) {
//        return esBoardService.titleContentSearchByQnA(title, pageable);
//    }
//
//    // 제목+내용(스터디)
//    @GetMapping("/search/study")
//    @ResponseBody
//    public SearchHits<EsBoard> titleContentSearchByStudy(
//            @RequestParam String title,
//            @PageableDefault(size = 20) Pageable pageable
//    ) {
//        return esBoardService.titleContentSearchByStudy(title, pageable);
//    }



    // 제목+내용+정렬 (지식공유)
    @GetMapping("/search/knowledge")
    @ResponseBody
    public ResponseEntity titleContentSearchByKnowledge(
            @RequestParam Integer sortType,
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        BaseRes baseRes =  esBoardService.titleContentSearchByKnowledge(sortType ,title, pageable);

        return ResponseEntity.ok().body(baseRes);
    }

    // 제목+내용+정렬 (QnA)
    @GetMapping("/search/qna")
    @ResponseBody
    public SearchHits<EsBoard> titleContentSearchByQnA(
            @RequestParam Integer sortType,
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esBoardService.titleContentSearchByQnA(sortType ,title, pageable);
    }

    // 제목+내용+정렬 (스터디)
    @GetMapping("/search/study")
    @ResponseBody
    public SearchHits<EsBoard> titleContentSearchByStudy(
            @RequestParam Integer sortType,
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esBoardService.titleContentSearchByStudy(sortType ,title, pageable);
    }

    // 제목+내용+정렬 (인기글)
    @GetMapping("/search/hot")
    @ResponseBody
    public SearchHits<EsBoard> titleContentSearchByHot(
            @RequestParam Integer sortType,
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esBoardService.titleContentSearchByHot(sortType ,title, pageable);
    }

    // 제목+내용+정렬 (통합)
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity titleContentSearch(
            @RequestParam Integer categoryIdx,
            @RequestParam Integer sortType,
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        BaseRes baseRes =  esBoardService.titleContentSearch(categoryIdx, sortType ,title, pageable);

        return ResponseEntity.ok().body(baseRes);
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
