package com.example.bootshelf.reviewsvc.review.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.reviewsvc.review.model.request.PatchUpdateReviewReq;
import com.example.bootshelf.reviewsvc.review.model.request.PostCreateReviewReq;
import com.example.bootshelf.reviewsvc.review.service.ReviewService;
import com.example.bootshelf.user.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "후기", description = "Review CRUD")
@Api(tags = "후기 게시판")
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "후기글 생성",
            description = "인증회원이 후기글을 생성할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<BaseRes> createReview(
            @RequestPart(value = "review") @Valid PostCreateReviewReq postCreateReviewReq,
            @RequestPart(value = "reviewImage", required = false) MultipartFile[] reviewImages
    ) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = reviewService.createReview(user, postCreateReviewReq, reviewImages);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "본인이 작성한 후기글 목록 조회",
            description = "인증회원은 본인이 작성한 후기글 목록을 조회할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/myList")
    public ResponseEntity<BaseRes> list(
            @PageableDefault(size = 9) Pageable pageable
    ) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = reviewService.myList(user, pageable);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "카테고리 별, 조건( 조회수, 추천수, 스크랩수, 댓글수) 별 후기글 목록 조회",
            description = "모든 사용자가 카테고리 별, 조건 별 후기글 목록을 조회할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/list/{reviewCategoryIdx}/{sortType}")
    public ResponseEntity<BaseRes> listReview(
            @PathVariable @NotNull(message = "후기 카테고리 IDX는 필수 입력 항목입니다.") @Positive(message = "후기 카테고리 IDX는 1이상의 양수입니다.") Integer reviewCategoryIdx,
            @PathVariable @NotNull(message = "조건 유형은 필수 입력 항목입니다.") @Positive(message = "조건 유형은 1이상의 양수입니다.") @ApiParam(value = "정렬유형 : 1 (최신순), 2 (추천수 순), 3 (조회수 순), 4 (스크랩수 순), 5 (댓글수 순)") Integer sortType,
            @PageableDefault(size = 9) Pageable pageable
    ) {
        BaseRes baseRes = reviewService.listReview(reviewCategoryIdx, sortType, pageable);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "검색어 별 후기글 목록 조회",
            description = "모든 사용자가 검색어를 입력하여 검색어에 해당하는 후기글 목록을 조회할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/{reviewCategoryIdx}/{sortType}/search")
    public ResponseEntity<BaseRes> searchReview(
            @PathVariable @NotNull(message = "후기 카테고리 IDX는 필수 입력 항목입니다.") @Positive(message = "후기 카테고리 IDX는 1이상의 양수입니다.") Integer reviewCategoryIdx,
            @PathVariable @NotNull(message = "조건 유형은 필수 입력 항목입니다.") @Positive(message = "조건 유형은 1이상의 양수입니다.") @ApiParam(value = "정렬유형 : 1 (최신순), 2 (추천수 순), 3 (조회수 순), 4 (스크랩수 순), 5 (댓글수 순)") Integer sortType,
            @RequestParam String searchTerm,
            @PageableDefault(size = 9) Pageable pageable
    ) {
        BaseRes baseRes = reviewService.searchReview(reviewCategoryIdx, sortType, searchTerm, pageable);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "Review 후기 검색어로 조회",
            description = "후기 게시글을 검색어(키워드)로 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping ("/search")
    public ResponseEntity<BaseRes> searchReviewListByQuery (
            @RequestParam String query,
            @RequestParam Integer searchType,
            @PageableDefault(size = 9) Pageable pageable
    ){
        return ResponseEntity.ok().body(reviewService.searchReviewListByQuery(query, searchType, pageable));
    }

    @Operation(summary = "후기글 상세 조회",
            description = "모든 사용자가 후기글 상세내용을 조회할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/{reviewIdx}")
    public ResponseEntity<BaseRes> readReview(
            @PathVariable @NotNull(message = "후기 IDX는 필수 입력 항목입니다.") @Positive(message = "후기 IDX는 1이상의 양수입니다.") Integer reviewIdx
    ) {
        BaseRes baseRes = reviewService.readReview(reviewIdx);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "본인이 작성한 후기글 수정",
            description = "인증회원은 본인이 작성한 후기글을 수정할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity<BaseRes> updateReview(
            @RequestPart(value = "review") @Valid PatchUpdateReviewReq patchUpdateReviewReq,
            @RequestPart(value = "reviewImage", required = false) MultipartFile reviewImage){
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = reviewService.updateReview(user, patchUpdateReviewReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "본인이 작성한 후기글 삭제",
            description = "인증회원은 본인이 작성한 후기글을 삭제할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{reviewIdx}")
    public ResponseEntity<BaseRes> deleteReview(
            @PathVariable @NotNull(message = "후기 IDX는 필수 입력 항목입니다.") @Positive(message = "후기 IDX는 1이상의 양수입니다.") Integer reviewIdx
    ) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = reviewService.deleteReview(user, reviewIdx);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "Review 본인 후기글 수정을 위한 상세 조회",
            description = "본인이 작성한 후기글을 수정하기 위해 작성한 후기글을 불러오는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @GetMapping("/mywrite/{reviewIdx}")
    public ResponseEntity<BaseRes> findReviewDetailByUserIdx(
            @PathVariable Integer reviewIdx
    ) {

        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = reviewService.findReviewDetailByUserIdx(reviewIdx, user);

        return ResponseEntity.ok().body(baseRes);
    }

}
