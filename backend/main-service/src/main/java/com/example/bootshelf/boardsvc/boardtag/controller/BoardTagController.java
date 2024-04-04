package com.example.bootshelf.boardsvc.boardtag.controller;

import com.example.bootshelf.boardsvc.boardtag.service.BoardTagService;
import com.example.bootshelf.common.BaseRes;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "게시글 태그", description = "BoardTag List")
@Api(tags = "게시글 태그 순위 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/main/boardtag")
@CrossOrigin("*")
public class BoardTagController {

    private final BoardTagService boardTagService;

    @Operation(summary = "게시글 태그를 인기순(많이 사용)으로 조회",
            description = "모든회원이 게시글에 사용한 태그를 인기순(많이 사용)으로 조회할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<BaseRes> listHotTag() {

        BaseRes baseRes = boardTagService.listHotTag();

        return ResponseEntity.ok().body(baseRes);
    }
}
