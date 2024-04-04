package com.example.bootshelf.esreview.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.esreview.model.entity.EsReview;
import com.example.bootshelf.esreview.service.EsReviewService;
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
@RequestMapping("/es/review")
public class EsReviewController {

    private final EsReviewService esReviewService;

    // 메인 검색(통합)
    @GetMapping("/search/main")
    @ResponseBody
    public ResponseEntity titleContentSearch(
            @RequestParam Integer selectedDropdownValue,
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        BaseRes baseRes =  esReviewService.titleSearchByMain(selectedDropdownValue, title, pageable);

        return ResponseEntity.ok().body(baseRes);
    }
//
//    // 제목+내용 (메인)
//    @GetMapping("/search/main/titlecontent")
//    @ResponseBody
//    public ResponseEntity titleContentSearchByMain(
//            @RequestParam String title,
//            @PageableDefault(size = 20) Pageable pageable
//    ) {
//        BaseRes baseRes =  esReviewService.titleContentSearchByMain(title, pageable);
//
//        return ResponseEntity.ok().body(baseRes);
//    }

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

    // 제목+내용+정렬 (통합)
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity titleContentSearch(
            @RequestParam Integer categoryIdx,
            @RequestParam Integer sortType,
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        BaseRes baseRes =  esReviewService.titleContentSearch(categoryIdx, sortType ,title, pageable);

        return ResponseEntity.ok().body(baseRes);
    }

}
