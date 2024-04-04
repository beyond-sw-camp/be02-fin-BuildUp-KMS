package com.example.bootshelf.adapter.in.web;

import com.example.bootshelf.adapter.out.es.entity.EsReview;
import com.example.bootshelf.application.port.in.SearchReviewUseCase;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@WebAdapter
@CrossOrigin("*")
@RequestMapping("/review")
public class SearchReviewController {

    private final SearchReviewUseCase searchReviewUseCase;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    public ResponseEntity<BaseRes> titleContentSearch(
            @RequestParam @NotNull @Positive Integer categoryIdx,
            @RequestParam @NotNull @Positive Integer sortType,
            @RequestParam @NotNull String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {

        BaseRes baseRes = searchReviewUseCase.titleContentSearch(categoryIdx, sortType, title, pageable);

        return ResponseEntity.ok().body(baseRes);
    }
}
