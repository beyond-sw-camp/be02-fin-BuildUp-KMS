package com.example.bootshelf.boardsvc.boardcategory.controller;

import com.example.bootshelf.boardsvc.boardcategory.model.request.PatchUpdateBoardCategoryReq;
import com.example.bootshelf.boardsvc.boardcategory.model.request.PostCreateBoardCategoryReq;
import com.example.bootshelf.boardsvc.boardcategory.service.BoardCategoryService;
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

@Tag(name = "관리자", description = "Board Category CRUD")
@Api(tags = "게시판 카테고리")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/admin/board")
public class BoardCategoryController {

    private final BoardCategoryService boardCategoryService;

    @Operation(summary = "게시판 카테고리 등록", description = "관리자는 게시판의 카테고리를 등록할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<BaseRes> createBoardCategory(
            @AuthenticationPrincipal User user,
            @RequestBody PostCreateBoardCategoryReq postCreateBoardCategoryReq
    ) {
        BaseRes baseRes = boardCategoryService.createBoardCategory(postCreateBoardCategoryReq);
        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "게시판 카테고리 전체 조회", description = "관리자는 게시판 카테고리를 전체 조회할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<BaseRes> listBoardCategory(
            @PageableDefault(size = 10) Pageable pageable
    ) {
        BaseRes baseRes = boardCategoryService.listBoardCategory(pageable);
        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "게시판 카테고리 수정", description = "관리자는 게시판의 카테고리를 수정할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update/{categoryBoardIdx}")
    public ResponseEntity<BaseRes> updateBoardCategory(@PathVariable  Integer categoryBoardIdx, @RequestBody PatchUpdateBoardCategoryReq patchUpdateBoardCategoryReq) {
        BaseRes baseRes = boardCategoryService.updateBoardCategory(patchUpdateBoardCategoryReq, categoryBoardIdx);
        return ResponseEntity.ok().body(baseRes);
    }


    @Operation(summary = "게시판 카테고리 삭제", description = "관리자는 게시판 카테고리를 삭제할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.DELETE,value = "/delete/{boardCategoryIdx}")
    public ResponseEntity<BaseRes> updateBoardCategory(@PathVariable Integer boardCategoryIdx) {
        BaseRes baseRes = boardCategoryService.deleteBoardCategory(boardCategoryIdx);
        return ResponseEntity.ok().body(baseRes);
    }

}
