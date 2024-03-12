package com.example.bootshelf.comment.controller;


import com.example.bootshelf.comment.model.request.PostCreateCommentReq;
import com.example.bootshelf.comment.service.CommentService;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.user.model.entity.User;
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
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "댓글 등록", response = BaseRes.class, notes = "회원은 후기 게시판에 작성되어 있는 게시글에 댓글을 작성할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/{reviewIdx}/comment/create")
    public ResponseEntity createComment(@PathVariable @NotNull @Positive Integer reviewIdx, @RequestBody PostCreateCommentReq commentCreateReq) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = commentService.createComment(user,reviewIdx, commentCreateReq);

        return ResponseEntity.ok().body(baseRes);
    }
}
