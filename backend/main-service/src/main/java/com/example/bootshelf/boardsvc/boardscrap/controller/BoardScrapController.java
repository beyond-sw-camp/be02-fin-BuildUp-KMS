package com.example.bootshelf.boardsvc.boardscrap.controller;

import com.example.bootshelf.boardsvc.boardscrap.model.request.PostCreateBoardScrapReq;
import com.example.bootshelf.boardsvc.boardscrap.service.BoardScrapService;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.user.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Tag(name = "게시판", description = "게시판 CRUD")
@Api(tags = "게시판 스크랩")
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
    public ResponseEntity<BaseRes> createBoardScrap(
            @AuthenticationPrincipal User user,
            @RequestBody PostCreateBoardScrapReq postCreateBoardScrapReq
    ) {
        return ResponseEntity.ok().body(boardScrapService.createBoardScrap(user, postCreateBoardScrapReq));
    }

    @Operation(summary = "BoardScrap 여부 조회",
            description = "게시글을 스크랩 여부를 확인하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/check/{boardIdx}")
    public ResponseEntity<BaseRes> checkBoardScrap(
            @AuthenticationPrincipal User user,
            @PathVariable Integer boardIdx
    ) {
        return ResponseEntity.ok().body(boardScrapService.checkBoardScrap(user, boardIdx));
    }

    @Operation(summary = "BoardScrap 스크랩 삭제",
            description = "스크랩한 게시판 게시글을 삭제하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PatchMapping("/delete/{boardScrapIdx}")
    public ResponseEntity<BaseRes> deleteBoardScrap(
            @AuthenticationPrincipal User user,
            @PathVariable Integer boardScrapIdx
    ) {
        return ResponseEntity.ok().body(boardScrapService.deleteBoardScrap(user, boardScrapIdx));
    }

    @Operation(summary = "BoardScrap 목록 카테고리별 조회",
            description = "스크랩한 게시판 게시글 목록을 카테고리별로 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/list/{boardCategoryIdx}/{sortType}")
    public ResponseEntity<BaseRes> findBoardScrapListByCategory(
            @AuthenticationPrincipal User user,
            @PageableDefault(size = 10) Pageable pageable,
            @PathVariable(value = "boardCategoryIdx") Integer boardCategoryIdx,
            @PathVariable @NotNull(message = "조건 유형은 필수 입력 항목입니다.") @Positive(message = "조건 유형은 1이상의 양수입니다.") @ApiParam(value = "정렬유형 : 1 (최신순), 2 (추천수 순), 3 (조회수 순), 4 (스크랩수 순), 5 (댓글수 순)") Integer sortType
    ) {
        return ResponseEntity.ok().body(boardScrapService.findBoardScrapListByCategory(user, boardCategoryIdx, sortType, pageable));
    }

    // 현재 미사용 API
//    @Operation(summary = "BoardScrap 목록 조회",
//            description = "스크랩한 게시판 게시글 목록을 조회하는 API입니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "성공"),
//            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
//    })
//    @GetMapping("/list")
//    public ResponseEntity<BaseRes> findBoardScrapList(
//            @AuthenticationPrincipal User user,
//            @PageableDefault(size = 5) Pageable pageable
//    ) {
//        return ResponseEntity.ok().body(boardScrapService.findBoardScrapList(user, pageable));
//    }
}
