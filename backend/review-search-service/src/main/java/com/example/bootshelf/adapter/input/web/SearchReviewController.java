package com.example.bootshelf.adapter.input.web;

import com.example.bootshelf.application.port.input.SearchReviewUseCase;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@WebAdapter
@CrossOrigin("*")
@RequestMapping("/search/review")
public class SearchReviewController {

    private final SearchReviewUseCase searchReviewUseCase;

    // 후기 페이지에서 검색.
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    @ResponseBody
    public ResponseEntity<BaseRes> searchReview(
            @RequestParam @NotNull @Positive Integer categoryIdx,
            @RequestParam @NotNull @Positive Integer sortType,
            @RequestParam @NotNull String title,
            @PageableDefault(size = 9) Pageable pageable
    ) {

        BaseRes baseRes = searchReviewUseCase.searchReview(categoryIdx, sortType, title, pageable);

        return ResponseEntity.ok().body(baseRes);
    }
}
