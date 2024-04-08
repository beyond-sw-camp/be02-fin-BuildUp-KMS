package com.example.bootshelf.adapter.input.web;


import com.example.bootshelf.application.port.input.SearchBoardUseCase;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
}
