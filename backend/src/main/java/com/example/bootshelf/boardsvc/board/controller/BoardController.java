package com.example.bootshelf.boardsvc.board.controller;

import com.example.bootshelf.boardsvc.board.model.request.PatchUpdateBoardReq;
import com.example.bootshelf.boardsvc.board.model.request.PostCreateBoardReq;
import com.example.bootshelf.boardsvc.board.model.response.PostCreateBoardRes;
import com.example.bootshelf.boardsvc.board.service.BoardService;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Tag(name = "Board", description = "Board 숙소 CRUD")
@Api(tags = "Board")
@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @Operation(summary = "Board 게시글 등록",
            description = "게시판에 게시글을 등록하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @PostMapping("/create")
    public ResponseEntity<BaseRes> createBoard(
            @AuthenticationPrincipal User user,
            @RequestPart(value = "board") PostCreateBoardReq postCreateBoardReq,
            @RequestPart(value = "boardImage", required = false) MultipartFile[] uploadFiles
    ) {
        BaseRes baseRes = boardService.createBoard(user, postCreateBoardReq, uploadFiles);
        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "Board 본인 게시글 전체 조회",
            description = "본인이 작성한 게시글을 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @RequestMapping(method = RequestMethod.GET, value = "/mylist/{sortType}")
    public ResponseEntity<BaseRes> myList(
            @PageableDefault(size = 9) Pageable pageable,
            @PathVariable @NotNull(message = "조건 유형은 필수 입력 항목입니다.") @Positive(message = "조건 유형은 1이상의 양수입니다.") @ApiParam(value = "정렬유형 : 1 (최신순), 2 (추천수 순), 3 (조회수 순), 4 (스크랩수 순), 5 (댓글수 순)") Integer sortType
    ) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = boardService.findMyBoardList(user, pageable, sortType);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "Board 본인 게시글 카테고리별 조회",
            description = "본인이 작성한 게시글을 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @RequestMapping(method = RequestMethod.GET, value = "/mylist/{boardCategoryIdx}/{sortType}")
    public ResponseEntity<BaseRes> myListByCategory(
            @PageableDefault(size = 9) Pageable pageable,
            @PathVariable(value = "boardCategoryIdx") Integer boardCategoryIdx,
            @PathVariable @NotNull(message = "조건 유형은 필수 입력 항목입니다.") @Positive(message = "조건 유형은 1이상의 양수입니다.") @ApiParam(value = "정렬유형 : 1 (최신순), 2 (추천수 순), 3 (조회수 순), 4 (스크랩수 순), 5 (댓글수 순)") Integer sortType
    ) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = boardService.findMyBoardListByCategory(user, pageable, boardCategoryIdx, sortType);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "Board 게시글 카테고리별 조회",
            description = "게시판의 게시글을 카테고리별로 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @RequestMapping(method = RequestMethod.GET, value = "/category/{boardCategoryIdx}/{sortType}")
    public ResponseEntity<BaseRes> boardListbyCategory(
            @PageableDefault(size = 9) Pageable pageable,
            @PathVariable(value = "boardCategoryIdx") Integer boardCategoryIdx,
            @PathVariable @NotNull(message = "조건 유형은 필수 입력 항목입니다.") @Positive(message = "조건 유형은 1이상의 양수입니다.") @ApiParam(value = "정렬유형 : 1 (최신순), 2 (추천수 순), 3 (조회수 순), 4 (스크랩수 순), 5 (댓글수 순)") Integer sortType
    ) {
        BaseRes baseRes = boardService.findListByCategory(pageable, boardCategoryIdx, sortType);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "Board 게시글 태그별 조회",
            description = "게시판의 게시글을 태그별로 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @RequestMapping(method = RequestMethod.GET, value = "/tag/{tagIdx}/{boardCategoryIdx}/{sortType}")
    public ResponseEntity<BaseRes> boardListbyTag(
            @PageableDefault(size = 9) Pageable pageable,
            @PathVariable Integer tagIdx,
            @PathVariable Integer boardCategoryIdx,
            @PathVariable @NotNull(message = "조건 유형은 필수 입력 항목입니다.") @Positive(message = "조건 유형은 1이상의 양수입니다.") @ApiParam(value = "정렬유형 : 1 (최신순), 2 (추천수 순), 3 (조회수 순), 4 (스크랩수 순), 5 (댓글수 순)") Integer sortType
    ) {
        BaseRes baseRes = boardService.findListByTag(pageable, tagIdx, boardCategoryIdx, sortType);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "Board 게시글 태그별 검색어 조회",
            description = "게시판의 게시글을 태그별로 검색어로 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @RequestMapping(method = RequestMethod.GET, value = "/tag/{tagIdx}/{boardCategoryIdx}/{sortType}/search")
    public ResponseEntity<BaseRes> boardSearchListbyTag(
            @PageableDefault(size = 9) Pageable pageable,
            @PathVariable Integer tagIdx,
            @PathVariable Integer boardCategoryIdx,
            @RequestParam String searchTerm,
            @PathVariable @NotNull(message = "조건 유형은 필수 입력 항목입니다.") @Positive(message = "조건 유형은 1이상의 양수입니다.") @ApiParam(value = "정렬유형 : 1 (최신순), 2 (추천수 순), 3 (조회수 순), 4 (스크랩수 순), 5 (댓글수 순)") Integer sortType
    ) {
        BaseRes baseRes = boardService.findSearchListByTag(pageable, tagIdx, boardCategoryIdx, searchTerm, sortType);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "Board 게시글 상세 조회",
            description = "게시판의 게시글을 게시글의 idx로 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @GetMapping("/{boardIdx}")
    public ResponseEntity<BaseRes> findBoardByIdx(
            @PathVariable Integer boardIdx
    ) {
        return ResponseEntity.ok().body(boardService.findBoardByIdx(boardIdx));
    }

    @Operation(summary = "Board 게시글 검색어로 조회",
            description = "게시판의 게시글을 검색어(키워드)로 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @GetMapping("/search")
    public ResponseEntity<BaseRes> searchBoardListByQuery(
            @RequestParam String query,
            @RequestParam Integer searchType,
            @PageableDefault(size = 20) Pageable pageable

    ) {
        return ResponseEntity.ok().body(boardService.searchBoardListByQuery(query, searchType, pageable));
    }

    @Operation(summary = "Board 카테고리별 게시글 검색어로 조회",
            description = "카테고리별 게시판의 게시글을 검색어(키워드)로 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @GetMapping("/search/by/{boardCategoryIdx}")
    public ResponseEntity<BaseRes> searchBoardListByQueryAndCategory(
            @RequestParam String query,
            @PathVariable(value = "boardCategoryIdx") Integer boardCategoryIdx,
            @PageableDefault(size = 9) Pageable pageable,
            @RequestParam @NotNull(message = "조건 유형은 필수 입력 항목입니다.") @Positive(message = "조건 유형은 1이상의 양수입니다.") @ApiParam(value = "정렬유형 : 1 (최신순), 2 (추천수 순), 3 (조회수 순), 4 (스크랩수 순), 5 (댓글수 순)") Integer sortType
    ) {
        return ResponseEntity.ok().body(boardService.searchBoardListByQueryAndCategory(boardCategoryIdx, query, sortType, pageable));
    }

    /**
     * 게시판 + 후기 검색 api (v2)
     * -> 페이지네이션 잘 안됨
     */
    @Operation(summary = "Board+Review 게시글 검색어로 조회 v2",
            description = "게시판+후기 데이터를 검색어(키워드)로 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @GetMapping("/searchv2")
    public ResponseEntity<BaseRes> searchResultListByQueryV2(
            @RequestParam String query,
            @RequestParam Integer searchType,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return ResponseEntity.ok().body(boardService.searchResultListByQueryV2(query, searchType, pageable));
    }

    @Operation(summary = "Board 게시글 수정 기능",
            description = "게시판의 게시글을 수정하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity<BaseRes> updateBoard(
            @RequestPart(value = "board") @Valid PatchUpdateBoardReq patchUpdateBoardReq,
            @RequestPart(value = "boardImage", required = false) MultipartFile boardImage)
    {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = boardService.updateBoard(user, patchUpdateBoardReq, boardImage);
        return ResponseEntity.ok().body(baseRes);
    }


    @Operation(summary = "Board 게시글 삭제 기능",
            description = "게시판의 게시글을 삭제하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{boardIdx}")
    public ResponseEntity<BaseRes> deleteBoard(
            @PathVariable(value = "boardIdx") Integer boardIdx
    ) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = boardService.deleteBoard(user, boardIdx);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "Board 본인 게시글 수정을 위한 상세 조회",
            description = "본인이 작성한 게시글을 수정하기 위해 작성한 게시글을 불러오는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @GetMapping("/mywrite/{boardIdx}")
    public ResponseEntity<BaseRes> findBoardDetailByUserIdx(
            @PathVariable Integer boardIdx
    ) {

        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = boardService.findBoardDetailByUserIdx(boardIdx, user);

        return ResponseEntity.ok().body(baseRes);
    }

    // 인기 게시글 용 조회 API
    @Operation(summary = "Board 인기 게시글 카테고리 별 조회",
            description = "게시판의 인기 게시글을 카테고리 별 추천수 별로 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @RequestMapping(method = RequestMethod.GET, value = "/hotlist/{boardCategoryIdx}/{sortType}")
    public ResponseEntity<BaseRes> listHotBoard(
            @PageableDefault(size = 9) Pageable pageable,
            @PathVariable @NotNull @Positive Integer boardCategoryIdx,
            @PathVariable @NotNull(message = "조건 유형은 필수 입력 항목입니다.") @Positive(message = "조건 유형은 1이상의 양수입니다.") @ApiParam(value = "정렬유형 : 1 (최신순), 2 (추천수 순), 3 (조회수 순), 4 (스크랩수 순), 5 (댓글수 순)") Integer sortType
    ) {
        BaseRes baseRes = boardService.listHotBoard(pageable, boardCategoryIdx, sortType);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "Board 인기 게시글 검색어로 조회",
            description = "게시판의 인기 게시글을 추천수 별로 검색어(키워드)로 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")})
    @GetMapping("/hotlist/{boardCategoryIdx}/{sortType}/search")
    public ResponseEntity<BaseRes> searchHotBoard(
            @PathVariable @NotNull(message = "후기 카테고리 IDX는 필수 입력 항목입니다.") @Positive(message = "후기 카테고리 IDX는 1이상의 양수입니다.") Integer boardCategoryIdx,
            @PathVariable @NotNull(message = "조건 유형은 필수 입력 항목입니다.") @Positive(message = "조건 유형은 1이상의 양수입니다.") @ApiParam(value = "정렬유형 : 1 (최신순), 2 (추천수 순), 3 (조회수 순), 4 (스크랩수 순), 5 (댓글수 순)") Integer sortType,
            @RequestParam String searchTerm,
            @PageableDefault(size = 9) Pageable pageable
    ) {
        BaseRes baseRes = boardService.searchHotBoard(boardCategoryIdx, searchTerm, sortType, pageable);
        return ResponseEntity.ok().body(baseRes);
    }
}
