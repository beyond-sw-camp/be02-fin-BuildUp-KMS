package com.example.bootshelf.review.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.review.model.request.PostCreateReviewReq;
import com.example.bootshelf.review.service.ReviewService;
import com.example.bootshelf.user.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@CrossOrigin("*")
@Api(value = "후기 컨트롤러 v1", tags = "후기 API")
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation(value = "후기 등록", response = BaseRes.class, notes = "인증회원은 수료한 과정 또는 강사에 대한 후기를 등록할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class))})})
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity createReview(
            @RequestPart(value = "review") @Valid PostCreateReviewReq postCreateReviewReq,
            @RequestPart(value = "reviewImage") MultipartFile[] reviewImages
    ) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = reviewService.createReview(user, postCreateReviewReq, reviewImages);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "본인이 작성한 후기글 목록 조회", response = BaseRes.class, notes = "인증회원은 본인이 작성한 후기글 목록을 조회할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class))})})

    @RequestMapping(method = RequestMethod.GET, value = "/myList")
    public ResponseEntity list(
        @PageableDefault(size = 10) Pageable pageable
    ) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = reviewService.myList(user, pageable);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "카테고리 별, 조건( 조회수, 추천수, 스크랩수, 댓글수) 별 후기글 목록 조회", response = BaseRes.class, notes = "모든 사용자가 카테고리 별, 조건 별 후기글 목록을 조회할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class))})})
    @RequestMapping(method = RequestMethod.GET, value = "/{reviewCategoryIdx}/{sortType}")
    public ResponseEntity listReview(
            @PathVariable @NotNull(message = "후기 카테고리 IDX는 필수 입력 항목입니다.") @Positive(message = "후기 카테고리 IDX는 1이상의 양수입니다.") Integer reviewCategoryIdx,
            @PathVariable @NotNull(message = "조건 유형은 필수 입력 항목입니다.") @Positive(message = "조건 유형은 1이상의 양수입니다.") Integer sortType,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        BaseRes baseRes = reviewService.listReview(reviewCategoryIdx, sortType, pageable);

        return ResponseEntity.ok().body(baseRes);
    }

}
