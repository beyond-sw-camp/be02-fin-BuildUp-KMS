package com.example.bootshelf.boardsvc.boardcomment.controller;

import com.example.bootshelf.boardsvc.boardcomment.model.request.PatchUpdateBoardCommentReq;
import com.example.bootshelf.boardsvc.boardcomment.model.request.PostCreateBoardCommentReq;
import com.example.bootshelf.boardsvc.boardcomment.model.request.PostCreateBoardReplyReq;
import com.example.bootshelf.boardsvc.boardcomment.service.BoardCommentService;
import com.example.bootshelf.common.BaseRes;
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Tag(name = "게시판", description = "게시판 댓글 CRUD")
@Api(tags = "게시판 댓글")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/board")
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @Operation(summary = "게시판 댓글 등록", description = "회원은 게시판에 작성되어 있는 게시글에 댓글을 작성할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/{boardIdx}/comment/create")
    public ResponseEntity createBoardComment(
            @AuthenticationPrincipal User user,
            @PathVariable @NotNull @Positive Integer boardIdx,
            @RequestBody @Valid PostCreateBoardCommentReq postCreateBoardCommentReq) {
        BaseRes baseRes = boardCommentService.createBoardComment(user, boardIdx, postCreateBoardCommentReq);

        return ResponseEntity.ok().body(baseRes);
    }


    @Operation(summary = "게시판 댓글 목록 조회", description = "회원/비회원은 게시글에 대한 전체 댓글을 조회할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/{boardIdx}/comment")
    public ResponseEntity listBoardComment(@PathVariable @NotNull @Positive Integer boardIdx) {
        BaseRes baseRes = boardCommentService.listBoardComment(boardIdx);
        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "게시판 댓글 수정", description = "회원은 자신이 등록한 댓글을 수정할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/{boardIdx}/update/{idx}")
    public ResponseEntity updateBoardComment(
            @AuthenticationPrincipal User user,
            @PathVariable @NotNull @Positive Integer boardIdx,
            @PathVariable @NotNull @Positive Integer idx,
            @RequestBody @Valid PatchUpdateBoardCommentReq patchUpdateBoardCommentReq) {
        BaseRes baseRes = boardCommentService.updateBoardComment(user, boardIdx, idx, patchUpdateBoardCommentReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "게시판 댓글 삭제", description = "회원은 등록한 댓글을 삭제할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/{boardIdx}/delete/{idx}")
    public ResponseEntity deleteBoardComment(
            @AuthenticationPrincipal User user,
            @PathVariable @NotNull @Positive Integer idx) {
        BaseRes baseRes = boardCommentService.deleteBoardComment(idx, user);

        return ResponseEntity.ok().body(baseRes);
    }


    @Operation(summary = "게시판 대댓글 등록", description = "회원은 후기 게시판에 작성되어 있는 게시글에 대댓글을 작성할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/{boardIdx}/comment/create/{parentIdx}")
    public ResponseEntity createBoardReply(
            @AuthenticationPrincipal User user,
            @PathVariable @NotNull @Positive Integer boardIdx,
            @RequestBody PostCreateBoardReplyReq postCreateBoardReplyReq,
            @PathVariable @NotNull @Positive Integer parentIdx) {
        BaseRes baseRes = boardCommentService.createBoardReply(user, boardIdx, parentIdx, postCreateBoardReplyReq);

        return ResponseEntity.ok().body(baseRes);
    }
}