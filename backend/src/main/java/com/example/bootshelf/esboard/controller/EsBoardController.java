package com.example.bootshelf.esboard.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.esboard.model.entity.EsBoard;
import com.example.bootshelf.esboard.service.EsBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
@RequestMapping("/es/board")
public class EsBoardController {
    private final EsBoardService esBoardService;

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

    // search after 적용
    @GetMapping("/search2")
    public ResponseEntity<BaseRes> titleContentSearch2(
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

        BaseRes baseRes = esBoardService.titleContentSearch2(categoryIdx, sortType, title, size, searchAfter);
        return ResponseEntity.ok().body(baseRes);
    }
}
