package com.example.bootshelf.tag.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.tag.model.request.PatchUpdateTagReq;
import com.example.bootshelf.tag.model.request.PostCreateTagReq;
import com.example.bootshelf.tag.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Tag(name = "태그", description = "Tag CRUD")
@Api(tags = "[관리자] 태그")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/tag")
@CrossOrigin("*")
public class TagController {

    private final TagService tagService;

    @Operation(summary = "[관리자] 태그 등록",
            description = "관리자는 태그를 등록할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @ApiOperation(value = "[관리자] 태그 등록", response = BaseRes.class, notes = "관리자는 태그를 등록할 수 있다.")
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<BaseRes> createTag(@RequestBody @Valid PostCreateTagReq postCreateTagReq) {

        com.example.bootshelf.tag.model.entity.Tag tag = tagService.createTag(postCreateTagReq.getTagName());

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("태그 등록 성공")
                .result(tag.getTagName())
                .build();

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "[관리자] 태그 목록 조회",
            description = "관리자는 태그 목록을 조회할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<BaseRes> listTag(@PageableDefault(size = 20) Pageable pageable) {

        BaseRes baseRes = tagService.listTag(pageable);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "[관리자] 태그 수정",
            description = "관리자는 태그명을 수정할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity<BaseRes> updateTag(@RequestBody @Valid PatchUpdateTagReq patchUpdateTagReq) {

        BaseRes baseRes = tagService.updateTag(patchUpdateTagReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "[관리자] 태그 삭제",
            description = "관리자는 태그를 삭제할 수 있다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{tagIdx}")
    public ResponseEntity<BaseRes> updateTag(
            @PathVariable @NotNull(message = "태그 IDX는 필수 입력 항목입니다.") @Positive(message = "태그 IDX는 1이상의 양수입니다.") Integer tagIdx
    ) {

        BaseRes baseRes = tagService.deleteTag(tagIdx);

        return ResponseEntity.ok().body(baseRes);
    }
}