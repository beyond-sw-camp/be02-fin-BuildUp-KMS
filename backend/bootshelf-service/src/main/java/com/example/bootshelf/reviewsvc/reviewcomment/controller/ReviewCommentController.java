package com.example.bootshelf.reviewsvc.reviewcomment.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.reviewsvc.reviewcomment.model.request.PatchUpdateReviewCommentReq;
import com.example.bootshelf.reviewsvc.reviewcomment.model.request.PostCreateReviewCommentReq;
import com.example.bootshelf.reviewsvc.reviewcomment.model.request.PostCreateReviewReplyReq;
import com.example.bootshelf.reviewsvc.reviewcomment.service.ReviewCommentService;
import com.example.bootshelf.user.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Tag(name = "리뷰 게시판", description = "리뷰 게시판 댓글 CRUD")
@Api(tags = "게시판 댓글")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/review")
public class ReviewCommentController {

    private final ReviewCommentService reviewCommentService;

    @Operation(summary = "후기 게시판 댓글 등록", description = "회원은 후기 게시판에 작성되어 있는 게시글에 댓글을 작성할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/{reviewIdx}/comment/create")
    public ResponseEntity createReviewComment(
            @AuthenticationPrincipal User user,
            @PathVariable @NotNull @Positive Integer reviewIdx,
            @RequestBody @Valid PostCreateReviewCommentReq postCreateReviewCommentReq) {
        BaseRes baseRes = reviewCommentService.createReviewComment(user, reviewIdx, postCreateReviewCommentReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "후기 게시판 댓글 목록 조회", description = "회원/비회원은 게시글에 대한 전체 댓글을 조회할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/{reviewIdx}/comment")
    public ResponseEntity listReviewComment(@PathVariable @NotNull @Positive Integer reviewIdx) {
        BaseRes baseRes = reviewCommentService.listComment(reviewIdx);
        return ResponseEntity.ok().body(baseRes);
    }



    @Operation(summary = "후기 게시판 댓글 수정", description = "회원은 등록한 댓글을 수정할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.PATCH, value = "/{reviewIdx}/update/{idx}")
    public ResponseEntity updateReviewComment(
            @AuthenticationPrincipal User user,
            @PathVariable @NotNull @Positive Integer reviewIdx,
            @PathVariable @NotNull @Positive Integer idx,
            @RequestBody @Valid PatchUpdateReviewCommentReq patchUpdateReviewCommentReq) {
        BaseRes baseRes = reviewCommentService.updateComment(user, reviewIdx, idx, patchUpdateReviewCommentReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "후기 게시판 댓글 삭제", description = "회원은 등록한 댓글을 삭제할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.PATCH, value = "/{reviewIdx}/delete/{idx}")
    public ResponseEntity deleteReview(
            @AuthenticationPrincipal User user,
            @PathVariable @NotNull @Positive Integer idx) {
        BaseRes baseRes =  reviewCommentService.deleteComment(user, idx);

            return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "후기 게시판 대댓글 등록", description = "회원은 대댓글을 등록할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/{reviewIdx}/comment/create/{parentIdx}")
    public ResponseEntity createReviewReply(
            @AuthenticationPrincipal User user,
            @PathVariable @NotNull @Positive Integer reviewIdx,
            @RequestBody PostCreateReviewReplyReq postCreateReviewReplyReq,
            @PathVariable @NotNull @Positive Integer parentIdx) {
        BaseRes baseRes = reviewCommentService.createReviewReply(user, reviewIdx, parentIdx, postCreateReviewReplyReq);

        return ResponseEntity.ok().body(baseRes);
    }
}
