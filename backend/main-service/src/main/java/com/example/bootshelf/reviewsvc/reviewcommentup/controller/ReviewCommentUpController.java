package com.example.bootshelf.reviewsvc.reviewcommentup.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.reviewsvc.reviewcommentup.model.request.PostCreateReviewCommentUpReq;
import com.example.bootshelf.reviewsvc.reviewcommentup.service.ReviewCommentUpService;
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

@Tag(name = "후기", description = "후기 CRUD")
@Api(tags = "후기 댓글 추천")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/reviewcomment/up")
public class ReviewCommentUpController {
    private final ReviewCommentUpService reviewCommentUpService;

    @Operation(summary = "ReviewCommentUp 후기 댓글 추천 등록",
            description = "후기 댓글을 추천하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "해당 후기 댓글이 존재하지 않음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/create")
    public ResponseEntity<BaseRes> createReviewCommentUp(
            @AuthenticationPrincipal User user,
            @RequestBody PostCreateReviewCommentUpReq postCreateReviewCommentUpReq
    ) {
        return ResponseEntity.ok().body(reviewCommentUpService.createReviewCommentUp(user, postCreateReviewCommentUpReq));
    }


    @Operation(summary = "ReviewCommentUp 후기 댓글 추천 목록 조회",
            description = "추천한 후기 댓글 목록을 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/list")
    public ResponseEntity<BaseRes> findReviewCommentUpList(
            @AuthenticationPrincipal User user,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok().body(reviewCommentUpService.findReviewCommentUpList(user, pageable));
    }


    @Operation(summary = "ReviewCommentUp 후기 댓글 추천 여부 조회",
            description = "후기 댓글 추천 여부를 확인하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/check/{reviewCommentIdx}")
    public ResponseEntity<BaseRes> checkReviewCommentUp(
            @AuthenticationPrincipal User user,
            @PathVariable Integer reviewCommentIdx
    ) {
        return ResponseEntity.ok().body(reviewCommentUpService.checkReviewCommentUp(user, reviewCommentIdx));
    }


    @Operation(summary = "ReviewCommentUp 후기 댓글 추천 삭제",
            description = "추천한 후기 댓글을 삭제하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PatchMapping("/delete/{reviewCommentIdx}")
    public ResponseEntity<BaseRes> deleteReviewCommentUp(
            @AuthenticationPrincipal User user,
            @PathVariable Integer reviewCommentIdx
    ) {
        return ResponseEntity.ok().body(reviewCommentUpService.deleteReviewCommentUp(user, reviewCommentIdx));
    }
}
