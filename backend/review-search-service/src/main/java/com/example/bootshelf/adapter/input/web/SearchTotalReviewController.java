package com.example.bootshelf.adapter.input.web;

import com.example.bootshelf.application.port.input.SearchTotalReviewUseCase;
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
public class SearchTotalReviewController {

    private final SearchTotalReviewUseCase searchTotalReviewUseCase;

    // 메인 페이지에서 검색
    @RequestMapping(method = RequestMethod.GET, value = "/total/list")
    @ResponseBody
    public ResponseEntity<BaseRes> searchTotalReview(
            @RequestParam @NotNull @Positive Integer selectedDropdownValue,
            @RequestParam @NotNull String title,
            @PageableDefault(size = 20) Pageable pageable
    ) {

        BaseRes baseRes = searchTotalReviewUseCase.searchTotalReview(selectedDropdownValue, title, pageable);

        return ResponseEntity.ok().body(baseRes);
    }
}
