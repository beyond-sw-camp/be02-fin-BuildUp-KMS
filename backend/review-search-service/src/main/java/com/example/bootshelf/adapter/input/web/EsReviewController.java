package com.example.bootshelf.adapter.input.web;

import com.example.bootshelf.application.port.input.EsReviewUseCase;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@WebAdapter
@CrossOrigin("*")
@RequestMapping("/search/review/es/review")
public class EsReviewController {
    private final EsReviewUseCase esReviewUseCase;

    @GetMapping("/v2/search/order")
    @ResponseBody
    public ResponseEntity titleContentSearch3(
            @RequestParam Integer selectedDropdownValue,
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

        BaseRes baseRes = esReviewUseCase.esSearchReview(selectedDropdownValue, sortType, title, size, searchAfter);
        return ResponseEntity.ok().body(baseRes);
    }

    @GetMapping("/v2/search/order2")
    @ResponseBody
    public ResponseEntity titleContentSearch4(
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

        BaseRes baseRes = esReviewUseCase.esSearchReview2(categoryIdx, sortType, title, size, searchAfter);
        return ResponseEntity.ok().body(baseRes);
    }
}
