package com.example.bootshelf.reviewscrap.controller;

import com.example.bootshelf.reviewscrap.model.request.PostCreateReviewScrapReq;
import com.example.bootshelf.reviewscrap.service.ReviewScrapService;
import com.example.bootshelf.user.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name="ReviewScrap", description = "ReviewScrap CRUD")
@Api(tags = "ReviewScrap")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/reviewscrap")
public class ReviewScrapController {
    private final ReviewScrapService reviewScrapService;

    @Operation(summary = "ReviewScrap 추가",
            description = "리뷰 게시글을 스크랩하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "해당 Review가 존재하지 않음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/create")
    public ResponseEntity createReviewScrap(
            @AuthenticationPrincipal User user,
            @RequestBody PostCreateReviewScrapReq postCreateReviewScrapReq
    ) {
        return ResponseEntity.ok().body(reviewScrapService.createReviewScrap(user, postCreateReviewScrapReq));
    }
}
