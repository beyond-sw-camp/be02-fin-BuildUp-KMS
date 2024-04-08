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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        BaseRes baseRes = esReviewService.titleSearchByMain(selectedDropdownValue, title, pageable);

        return ResponseEntity.ok().body(baseRes);
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
        BaseRes baseRes = esReviewService.titleContentSearch(categoryIdx, sortType ,title, pageable);

        return ResponseEntity.ok().body(baseRes);
    }


    /**
     *  search after 적용
     */
    // 메인 검색(통합)
    @GetMapping("/search2/main")
    @ResponseBody
    public ResponseEntity titleContentSearch2(
            @RequestParam Integer selectedDropdownValue,
            @RequestParam String title,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestParam(required = false) String searchAfterStr
    ) {
        List<Object> searchAfter = null;

        if (searchAfterStr != null && !searchAfterStr.isEmpty()) {
            searchAfter = Arrays.stream(searchAfterStr.split(","))
                    .collect(Collectors.toList());
        }

        BaseRes baseRes = esReviewService.titleSearchByMain2(selectedDropdownValue, title, size, searchAfter);
        return ResponseEntity.ok().body(baseRes);
    }


    // 제목+내용+정렬 (통합)
    @GetMapping("/search2")
    @ResponseBody
    public ResponseEntity titleContentSearch2(
            @RequestParam Integer categoryIdx,
            @RequestParam Integer sortType,
            @RequestParam String title,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestParam(required = false) String searchAfterStr
    ) {
        List<Object> searchAfter = null;

        if (searchAfterStr != null && !searchAfterStr.isEmpty()) {
            searchAfter = Arrays.stream(searchAfterStr.split(","))
                    .collect(Collectors.toList());
        }

        BaseRes baseRes = esReviewService.titleContentSearch2(categoryIdx, sortType ,title, size, searchAfter);
        return ResponseEntity.ok().body(baseRes);
    }
}
