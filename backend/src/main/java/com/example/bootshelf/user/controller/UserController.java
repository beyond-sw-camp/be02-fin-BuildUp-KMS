package com.example.bootshelf.user.controller;


import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.model.request.*;
import com.example.bootshelf.user.service.EmailVerifyService;
import com.example.bootshelf.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
@Api(value="회원 컨트롤러 v1", tags="회원 API")
public class UserController {

    private final UserService userService;
    private final EmailVerifyService emailVerifyService;

    @Operation(summary = "회원 가입",
            description = "회원이 회원 정보를 입력하여 회원 가입을 진행한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<BaseRes> signup(
            @RequestPart(value = "user") @Valid PostSignUpUserReq postSignUpUserReq,
            @RequestPart(value = "profileImage") MultipartFile profileImage) {
        BaseRes baseRes = userService.signup(postSignUpUserReq, profileImage);
        // 인증메일 발송
        userService.sendEmail(postSignUpUserReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "이메일 인증",
            description = "회원이 이메일 인증을 진행한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/verify")
    public RedirectView verify(GetEmailVerifyReq getEmailVerifyReq) {
        if (emailVerifyService.verify(getEmailVerifyReq)) {
            BaseRes baseRes = userService.updateStatus(getEmailVerifyReq.getEmail()); // 이메일 인증이 완료되면 회원의 status를 바꿔줌

            return new RedirectView("http://localhost:8081/");
        } else {

            return new RedirectView("http://localhost:8081/email/verify");
        }
    }

    @Operation(summary = "로그인",
            description = "회원이 로그인을 시도한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<BaseRes> login(@RequestBody @Valid PostLoginUserReq postLoginUserReq) {

        BaseRes baseRes = userService.login(postLoginUserReq);
        return ResponseEntity.ok().body(baseRes);
    }
    @Operation(summary = "회원 목록 조회",
            description = "관리자가 전체 회원의 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/list/{page}/{size}")
    public ResponseEntity<BaseRes> list(@PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size) {

        BaseRes baseRes = userService.list(page, size);
        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "회원 정보 조회",
            description = "회원이 본인의 회원 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity<BaseRes> read() {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = userService.read(user.getEmail());

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "회원 정보 수정",
            description = "회원이 본인의 회원 정보를 수정한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity<BaseRes> update( @RequestPart(value = "user") @Valid PatchUpdateUserReq patchUpdateUserReq,
                                  @RequestPart(value = "profileImage") MultipartFile profileImage) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = userService.update(user.getEmail(), patchUpdateUserReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "회원 탈퇴",
            description = "회원이 회원 탈퇴를 진행한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/cancel")
    public ResponseEntity<BaseRes> cancel() {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return ResponseEntity.ok().body(userService.cancel(user.getIdx()));
    }

    @Operation(summary = "회원 삭제",
            description = "관리자가 탈퇴한 회원의 정보를 삭제한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{userIdx}")
    public ResponseEntity<BaseRes> delete(@PathVariable @NotNull @Positive Integer userIdx) {
        BaseRes baseRes = userService.delete(userIdx);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "회원 정보 수정 시 비밀번호 확인",
            description = "회원이 회원 정보를 수정하기 위해서 비밀번호를 입력한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/checkPw")
    public ResponseEntity checkPassword(@RequestBody @Valid PostCheckPasswordReq postCheckPasswordReq) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return ResponseEntity.ok().body(userService.checkPassword(user, postCheckPasswordReq));
    }

}
