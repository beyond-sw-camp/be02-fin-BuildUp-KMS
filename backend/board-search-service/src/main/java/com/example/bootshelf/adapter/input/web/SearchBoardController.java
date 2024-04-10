package com.example.bootshelf.adapter.input.web;


import com.example.bootshelf.application.port.input.SearchBoardUseCase;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@WebAdapter
@CrossOrigin("*")
@RequestMapping("/search/board")
public class SearchBoardController {

    private final SearchBoardUseCase searchBoardUseCase;


    // 제목+내용+정렬 (통합)
    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity titleContentSearch(
            @RequestParam Integer categoryIdx,
            @RequestParam Integer sortType,
            @RequestParam String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        BaseRes baseRes =  searchBoardUseCase.searchBoard(categoryIdx, sortType, title, pageable);

        return ResponseEntity.ok().body(baseRes);
    }

    // search after 적용
    @GetMapping("/list/scroll")
    public ResponseEntity<BaseRes> titleContentSearchAfter(
            @RequestParam Integer categoryIdx,
            @RequestParam Integer sortType,
            @RequestParam String title,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(required = false) String searchAfterStr
    ) {
        List<Object> searchAfter = null;

        if (searchAfterStr != null && !searchAfterStr.isEmpty()) {
            searchAfter = Arrays.stream(searchAfterStr.split(","))
                    .collect(Collectors.toList());
        }

        BaseRes baseRes = searchBoardUseCase.searchAfterBoard(categoryIdx, sortType, title, size, searchAfter);
        return ResponseEntity.ok().body(baseRes);

    }
}
