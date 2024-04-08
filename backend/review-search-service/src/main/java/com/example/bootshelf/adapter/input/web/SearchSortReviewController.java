package com.example.bootshelf.adapter.input.web;

import com.example.bootshelf.application.port.input.SearchSortReviewUseCase;
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
public class SearchSortReviewController {

    private final SearchSortReviewUseCase searchSortReviewUseCase;

    // 제목+내용+정렬 (메인에서 검색 후 결과에서 또 검색)
    @RequestMapping(method = RequestMethod.GET, value = "/sort/list")
    @ResponseBody
    public ResponseEntity<BaseRes> searchSortReview(
            @RequestParam @NotNull @Positive Integer sortType,
            @RequestParam @NotNull String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {

        BaseRes baseRes = searchSortReviewUseCase.searchSortReview(sortType, title, pageable);

        return ResponseEntity.ok().body(baseRes);
    }
}
