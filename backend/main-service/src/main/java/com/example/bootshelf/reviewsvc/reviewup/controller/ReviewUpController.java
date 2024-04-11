package com.example.bootshelf.reviewsvc.reviewup.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.reviewsvc.reviewup.model.request.PostCreateReviewUpReq;
import com.example.bootshelf.reviewsvc.reviewup.service.ReviewUpService;
import com.example.bootshelf.user.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "후기", description = "후기 CRUD")
@Api(tags = "후기 추천")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/main/reviewup")
public class ReviewUpController {
    private final ReviewUpService reviewUpService;

    @Operation(summary = "ReviewUp 후기 추천 추가",
            description = "게시판 게시글을 추천하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "해당 후기가 존재하지 않음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/create")
    public ResponseEntity<BaseRes> createReviewUp(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid PostCreateReviewUpReq postCreateReviewUpReq
    ) {
        return ResponseEntity.ok().body(reviewUpService.createReviewUp(user, postCreateReviewUpReq));
    }


    @Operation(summary = "ReviewUp 후기 추천 목록 조회",
            description = "추천한 후기 목록을 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/list")
    public ResponseEntity<BaseRes> findReviewUpList(
            @AuthenticationPrincipal User user,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok().body(reviewUpService.findReviewUpList(user, pageable));
    }


    @Operation(summary = "ReviewUp 후기 추천 여부 조회",
            description = "후기 추천 여부를 확인하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/check/{reviewIdx}")
    public ResponseEntity<BaseRes> checkReviewUp(
            @AuthenticationPrincipal User user,
            @PathVariable Integer reviewIdx
    ) {
        return ResponseEntity.ok().body(reviewUpService.checkReviewUp(user, reviewIdx));
    }


    @Operation(summary = "ReviewUp 후기 추천 삭제",
            description = "추천한 후기 게시글을 삭제하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PatchMapping("/delete/{reviewIdx}")
    public ResponseEntity<BaseRes> deleteReviewUp(
            @AuthenticationPrincipal User user,
            @PathVariable Integer reviewIdx
    ) {
        return ResponseEntity.ok().body(reviewUpService.deleteReviewUp(user, reviewIdx));
    }
}
