package com.example.bootshelf.boardsvc.boardup.controller;

import com.example.bootshelf.boardsvc.boardup.model.request.PostCreateBoardUpReq;
import com.example.bootshelf.boardsvc.boardup.service.BoardUpService;
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

@Tag(name = "게시판", description = "게시판 CRUD")
@Api(tags = "게시판 추천")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/boardup")
public class BoardUpController {

    private final BoardUpService boardUpService;

    @Operation(summary = "BoardUp 게시글 추천 추가",
            description = "게시판 게시글을 추천하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "해당 게시글이 존재하지 않음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/create")
    public ResponseEntity<BaseRes> createBoardUp(
            @AuthenticationPrincipal User user,
            @RequestBody PostCreateBoardUpReq postCreateBoardUpReq
    ) {
        return ResponseEntity.ok().body(boardUpService.createBoardUp(user, postCreateBoardUpReq));
    }

    // 현재 미사용 API
//    @Operation(summary = "BoardUp 게시글 추천 목록 조회",
//            description = "추천한 게시판 게시글 목록을 조회하는 API입니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "성공"),
//            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
//    })
//    @GetMapping("/list")
//    public ResponseEntity<BaseRes> findBoardUpList(
//            @AuthenticationPrincipal User user,
//            @PageableDefault(size = 10) Pageable pageable
//    ) {
//        return ResponseEntity.ok().body(boardUpService.findBoardUpList(user, pageable));
//    }


    @Operation(summary = "BoardUp 게시글 추천 여부 조회",
            description = "게시글 추천 여부를 확인하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/check/{boardIdx}")
    public ResponseEntity<BaseRes> checkBoardUp(
            @AuthenticationPrincipal User user,
            @PathVariable Integer boardIdx
    ) {
        return ResponseEntity.ok().body(boardUpService.checkBoardUp(user, boardIdx));
    }


    @Operation(summary = "BoardUp 게시글 추천 삭제",
            description = "추천한 게시판 게시글을 삭제하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PatchMapping("/delete/{boardUpIdx}")
    public ResponseEntity<BaseRes> deleteBoardUp(
            @AuthenticationPrincipal User user,
            @PathVariable Integer boardUpIdx
    ) {
        return ResponseEntity.ok().body(boardUpService.deleteBoardUp(user, boardUpIdx));
    }
}
