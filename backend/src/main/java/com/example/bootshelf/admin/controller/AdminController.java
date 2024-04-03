package com.example.bootshelf.admin.controller;


import com.example.bootshelf.admin.model.request.PatchUpdateAdminReq;
import com.example.bootshelf.admin.model.request.PostSignUpAdminReq;
import com.example.bootshelf.common.BaseRes;

import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.model.request.PatchUpdateUserReq;
import com.example.bootshelf.user.model.request.PostLoginUserReq;
import com.example.bootshelf.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Tag(name = "관리자", description = "Admin CRUD")
@Api(tags = "관리자 회원기능")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    private final UserService userService;

    @Operation(summary = "관리자 회원 가입", description = "관리자가 정보를 입력하여 회원 가입을 진행한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<BaseRes> signup(
            @RequestBody @Valid PostSignUpAdminReq postSignUpAdminReq
    ) {
        BaseRes baseRes = userService.adminSignup(postSignUpAdminReq);
        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "관리자 로그인", description = "관리자가 로그인을 시도한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<BaseRes> login(@RequestBody @Valid PostLoginUserReq postLoginUserReq) {

        BaseRes baseRes = userService.login(postLoginUserReq);
        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "관리자 정보 수정",
            description = "관리자가 본인의 회원 정보를 수정한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity<BaseRes> update(@RequestPart(value = "admin") @Valid PatchUpdateUserReq patchUpdateUserReq
    ) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = userService.update(user.getEmail(), patchUpdateUserReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "관리자 삭제", description = "관리자 정보를 삭제한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{userIdx}")
    public ResponseEntity<BaseRes> delete(@PathVariable @NotNull @Positive Integer userIdx) {
        BaseRes baseRes = userService.delete(userIdx);

        return ResponseEntity.ok().body(baseRes);
    }

}
