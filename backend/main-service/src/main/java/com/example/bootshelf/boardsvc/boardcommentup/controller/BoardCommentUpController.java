package com.example.bootshelf.boardsvc.boardcommentup.controller;

import com.example.bootshelf.boardsvc.boardcommentup.model.request.PostCreateBoardCommentUpReq;
import com.example.bootshelf.boardsvc.boardcommentup.service.BoardCommentUpService;
import com.example.bootshelf.common.BaseRes;
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

@Tag(name = "게시판", description = "게시판 CRUD")
@Api(tags = "게시판 댓글 추천")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/boardcomment/up")
public class BoardCommentUpController {
    private final BoardCommentUpService boardCommentUpService;

    @Operation(summary = "BoardCommentUp 게시글 댓글 추천 등록",
            description = "게시글 댓글을 추천하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "해당 게시글 댓글이 존재하지 않음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/create")
    public ResponseEntity<BaseRes> createBoardCommentUp(
            @AuthenticationPrincipal User user,
            @RequestBody PostCreateBoardCommentUpReq postCreateBoardCommentUpReq
    ) {
        return ResponseEntity.ok().body(boardCommentUpService.createBoardCommentUp(user, postCreateBoardCommentUpReq));
    }


    @Operation(summary = "BoardCommentUp 게시글 댓글 추천 목록 조회",
            description = "추천한 게시판 댓글 목록을 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/list")
    public ResponseEntity<BaseRes> findBoardCommentUpList(
            @AuthenticationPrincipal User user,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok().body(boardCommentUpService.findBoardCommentUpList(user, pageable));
    }


    @Operation(summary = "BoardCommentUp 게시글 댓글 추천 여부 조회",
            description = "게시글 댓글 추천 여부를 확인하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/check/{boardCommentIdx}")
    public ResponseEntity<BaseRes> checkBoardCommentUp(
            @AuthenticationPrincipal User user,
            @PathVariable Integer boardCommentIdx
    ) {
        return ResponseEntity.ok().body(boardCommentUpService.checkBoardCommentUp(user, boardCommentIdx));
    }


    @Operation(summary = "BoardCommentUp 게시글 댓글 추천 삭제",
            description = "추천한 게시판 댓글을 삭제하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PatchMapping("/delete/{boardCommentUpIdx}")
    public ResponseEntity<BaseRes> deleteBoardCommentUp(
            @AuthenticationPrincipal User user,
            @PathVariable Integer boardCommentUpIdx
    ) {
        return ResponseEntity.ok().body(boardCommentUpService.deleteBoardCommentUp(user, boardCommentUpIdx));
    }
}
