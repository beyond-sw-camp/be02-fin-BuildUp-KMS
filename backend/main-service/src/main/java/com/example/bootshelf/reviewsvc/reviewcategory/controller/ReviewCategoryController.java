package com.example.bootshelf.reviewsvc.reviewcategory.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.reviewsvc.reviewcategory.model.request.PatchUpdateReviewCategoryReq;
import com.example.bootshelf.reviewsvc.reviewcategory.model.request.PostCreateReviewCategoryReq;
import com.example.bootshelf.reviewsvc.reviewcategory.service.ReviewCategoryService;
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
import org.springframework.web.bind.annotation.*;

@Tag(name = "관리자", description = "Review Category CRUD")
@Api(tags = "후기 카테고리")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/main/admin/review")
public class ReviewCategoryController {

    private final ReviewCategoryService reviewCategoryService;

    @Operation(summary = "후기 카테고리 등록", description = "관리자는 후기 게시판의 카테고리를 등록할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<BaseRes> createReviewCategory(
            @RequestBody PostCreateReviewCategoryReq postCreateReviewCategoryReq
    ) {
        BaseRes baseRes = reviewCategoryService.createReviewCategory(postCreateReviewCategoryReq);
        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "게시판 카테고리 전체 조회", description = "관리자는 게시판 카테고리를 전체 조회할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<BaseRes> listReviewCategory(
            @PageableDefault(size = 10) Pageable pageable
    ) {
        BaseRes baseRes = reviewCategoryService.listReviewCategory(pageable);
        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "게시판 카테고리 수정", description = "관리자는 게시판의 카테고리를 수정할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update/{reviewCategoryIdx}")
    public ResponseEntity<BaseRes> updateReviewCategory(@PathVariable  Integer reviewCategoryIdx, @RequestBody PatchUpdateReviewCategoryReq patchUpdateReviewCategoryReq) {
        BaseRes baseRes = reviewCategoryService.updateReviewCategory(patchUpdateReviewCategoryReq, reviewCategoryIdx);
        return ResponseEntity.ok().body(baseRes);
    }


    @Operation(summary = "게시판 카테고리 삭제", description = "관리자는 게시판 카테고리를 삭제할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.DELETE,value = "/delete/{reviewCategoryIdx}")
    public ResponseEntity<BaseRes> deleteReviewCategory(@PathVariable Integer reviewCategoryIdx) {
        BaseRes baseRes = reviewCategoryService.deleteReviewCategory(reviewCategoryIdx);
        return ResponseEntity.ok().body(baseRes);
    }

}
