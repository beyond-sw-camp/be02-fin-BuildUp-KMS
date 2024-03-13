package com.example.bootshelf.boardsvc.boardcomment.controller;

import com.example.bootshelf.boardsvc.boardcomment.model.request.PatchUpdateBoardCommentReq;
import com.example.bootshelf.boardsvc.boardcomment.model.request.PostCreateBoardCommentReq;
import com.example.bootshelf.boardsvc.boardcomment.model.request.PostCreateBoardReplyReq;
import com.example.bootshelf.boardsvc.boardcomment.service.BoardCommentService;
import com.example.bootshelf.common.BaseRes;
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@CrossOrigin("*")
@Api(value = "게시글 댓글 컨트롤러 v1", tags = "게시글 댓글 API")
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @ApiOperation(value = "댓글 등록", response = BaseRes.class, notes = "회원은 게시판에 작성되어 있는 게시글에 댓글을 작성할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/{boardIdx}/comment/create")
    public ResponseEntity createBoardComment(@PathVariable @NotNull @Positive Integer boardIdx, @RequestBody @Valid PostCreateBoardCommentReq postCreateBoardCommentReq) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = boardCommentService.createBoardComment(user, boardIdx, postCreateBoardCommentReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "댓글 목록 조회", response = BaseRes.class, notes = "회원/비회원은 게시글에 대한 전체 댓글을 조회할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/{boardIdx}/comment")
    public ResponseEntity listBoardComment(@PathVariable @NotNull @Positive Integer boardIdx) {
        BaseRes baseRes = boardCommentService.listBoardComment(boardIdx);
        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "댓글 수정", response = BaseRes.class, notes = "회원은 등록한 댓글을 수정할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.PATCH, value = "/{boardIdx}/update/{idx}")
    public ResponseEntity updateBoardComment(@PathVariable @NotNull @Positive Integer boardIdx, @PathVariable @NotNull @Positive Integer idx, @RequestBody @Valid PatchUpdateBoardCommentReq patchUpdateBoardCommentReq) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = boardCommentService.updateBoardComment(user, boardIdx, idx, patchUpdateBoardCommentReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "댓글 삭제", response = BaseRes.class, notes = "회원은 등록한 댓글을 삭제할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/{boardIdx}/delete/{idx}")
    public ResponseEntity deleteBoardComment(@PathVariable @NotNull @Positive Integer idx) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes =  boardCommentService.deleteBoardComment(idx, user);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "대댓글 등록", response = BaseRes.class, notes = "회원은 후기 게시판에 작성되어 있는 게시글에 대댓글을 작성할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/{boardIdx}/comment/create/{parentIdx}")
    public ResponseEntity createBoardReply(@PathVariable @NotNull @Positive Integer boardIdx, PostCreateBoardReplyReq postCreateBoardReplyReq, @PathVariable @NotNull @Positive Integer parentIdx) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = boardCommentService.createBoardReply(user, boardIdx, parentIdx, postCreateBoardReplyReq);

        return ResponseEntity.ok().body(baseRes);
    }
}
