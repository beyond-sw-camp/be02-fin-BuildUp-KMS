package com.example.bootshelf.boardscrap.controller;

import com.example.bootshelf.boardscrap.model.request.PostCreateBoardScrapReq;
import com.example.bootshelf.boardscrap.service.BoardScrapService;
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
import org.springframework.web.bind.annotation.*;

@Tag(name = "BoardScrap", description = "BoardScrap CRUD")
@Api(tags = "BoardScrap")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/boardscrap")
public class BoardScrapController {
    private final BoardScrapService boardScrapService;

    @Operation(summary = "BoardScrap 추가",
            description = "게시판 게시글을 스크랩하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "해당 게시글이 존재하지 않음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/create")
    public ResponseEntity createBoardScrap(
            @AuthenticationPrincipal User user,
            @RequestBody PostCreateBoardScrapReq postCreateBoardScrapReq
    ) throws Exception {
        return ResponseEntity.ok().body(boardScrapService.createBoardScrap(user, postCreateBoardScrapReq));
    }

    @Operation(summary = "BoardScrap 목록 조회",
            description = "스크랩한 게시판 게시글 목록을 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/list")
    public ResponseEntity findBoardScrapList(
            @AuthenticationPrincipal User user,
            @PageableDefault(size = 10) Pageable pageable
    ) throws Exception {
        return ResponseEntity.ok().body(boardScrapService.findBoardScrapList(user, pageable));
    }

    @Operation(summary = "BoardScrap 스크랩 삭제",
            description = "스크랩한 게시판 게시글을 삭제하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PatchMapping("/delete/{boardScrapIdx}")
    public ResponseEntity deleteBoardScrap(
            @AuthenticationPrincipal User user,
            @PathVariable Integer boardScrapIdx
    ) throws Exception {
        return ResponseEntity.ok().body(boardScrapService.deleteBoardScrap(user, boardScrapIdx));
    }
}
