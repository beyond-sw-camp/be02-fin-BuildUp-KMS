package com.example.bootshelf.reviewcomment.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.reviewcomment.model.request.PostCreateReviewCommentReq;
import com.example.bootshelf.reviewcomment.service.ReviewCommentService;
import com.example.bootshelf.user.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@CrossOrigin("*")
@Api(value = "후기글 댓글 컨트롤러 v1", tags = "후기글 댓글 API")
public class ReviewCommentController {

    private final ReviewCommentService reviewCommentService;

    @ApiOperation(value = "댓글 등록", response = BaseRes.class, notes = "회원은 후기 게시판에 작성되어 있는 게시글에 댓글을 작성할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/{reviewIdx}/comment/create")
    public ResponseEntity createComment(@PathVariable @NotNull @Positive Integer reviewIdx, PostCreateReviewCommentReq postCreateReviewCommentReq) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = reviewCommentService.createComment(user, reviewIdx, postCreateReviewCommentReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "댓글 목록 조회", response = BaseRes.class, notes = "회원/비회원은 게시글에 대한 전체 댓글을 조회할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/{reviewIdx}")
    public ResponseEntity listComment(@PathVariable @NotNull @Positive Integer reviewIdx) {
        BaseRes baseRes = reviewCommentService.listComment(reviewIdx);
        return ResponseEntity.ok().body(baseRes);
    }
}
