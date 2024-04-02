package com.example.bootshelf.esReview.controller;

import com.example.bootshelf.es.model.entity.EsBoard;
import com.example.bootshelf.esReview.model.entity.EsReview;
import com.example.bootshelf.esReview.service.EsReviewService;
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
@RequestMapping("/es/review")
public class EsReviewController {

    private final EsReviewService esReviewService;

    // 제목으로 검색(메인)
    @GetMapping("/search")
    @ResponseBody
    public SearchHits<EsReview> titleSearchByMain(
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esReviewService.titleSearchByMain(title, pageable);
    }

    // 제목+내용 (메인)
    @GetMapping("/search/title/content")
    @ResponseBody
    public SearchHits<EsReview> titleContentSearchByMain(
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esReviewService.titleContentSearchByMain(title, pageable);
    }

    // 제목+내용+정렬 (과정후기)
    @GetMapping("/search/course")
    @ResponseBody
    public SearchHits<EsReview> titleContentSearchByCourse(
            @RequestParam Integer sortType,
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esReviewService.titleContentSearchByCourse(sortType ,title, pageable);
    }

    // 제목+내용+정렬 (강사후기)
    @GetMapping("/search/teacher")
    @ResponseBody
    public SearchHits<EsReview> titleContentSearchByTeacher(
            @RequestParam Integer sortType,
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return esReviewService.titleContentSearchByTeacher(sortType ,title, pageable);
    }

}
