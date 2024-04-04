package com.example.bootshelf.user.controller;


import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.config.naver.NaverOcrApi;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.model.request.*;
import com.example.bootshelf.user.service.EmailVerifyService;
import com.example.bootshelf.user.service.SendEmailService;
import com.example.bootshelf.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
@Api(value="회원 컨트롤러 v1", tags="회원 API")
public class UserController {

    private final UserService userService;
    private final EmailVerifyService emailVerifyService;
    private final SendEmailService sendEmailService;

    @Operation(summary = "회원 가입",
            description = "회원이 회원 정보를 입력하여 회원 가입을 진행한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<BaseRes> signup(
            @RequestPart(value = "user") @Valid PostSignUpUserReq postSignUpUserReq,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage) {
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
            userService.updateStatus(getEmailVerifyReq.getEmail()); // 이메일 인증이 완료되면 회원의 status를 바꿔줌

            return new RedirectView("http://192.168.0.61/");
        } else {

            return new RedirectView("http://192.168.0.61/email/verify");
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
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<BaseRes> list(@PageableDefault(size = 20) Pageable pageable) {

        BaseRes baseRes = userService.list(pageable);
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
    public ResponseEntity<BaseRes> update( @RequestBody @Valid PatchUpdateUserReq patchUpdateUserReq ) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = userService.update(user.getEmail(), patchUpdateUserReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "회원 프로필 이미지 수정",
            description = "회원이 본인의 회원 프로필 이미지를 수정한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update/image")
    public ResponseEntity<BaseRes> update( @RequestPart(value = "profileImage") MultipartFile profileImage) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = userService.updateImage(user.getEmail(), profileImage);

        return ResponseEntity.ok().body(baseRes);
    }

    @Operation(summary = "회원 탈퇴",
            description = "회원이 회원 탈퇴를 진행한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/cancel")
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
    @RequestMapping(method = RequestMethod.PATCH, value = "/delete/{userIdx}")
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
    @RequestMapping(method = RequestMethod.POST, value = "/checkpw")
    public ResponseEntity checkPassword(@RequestBody @Valid PostCheckPasswordReq postCheckPasswordReq) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return ResponseEntity.ok().body(userService.checkPassword(user, postCheckPasswordReq));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/check/course")
    public ResponseEntity checkCourse(@RequestPart("courseImage") MultipartFile file) throws IOException {

        String result = NaverOcrApi.callApi("POST", file.getBytes(), "VHlLZ3BiR0tGT3lNaVFxeUFhVUx3cVpTRlBRWmlFYWQ=", "jpg");

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("Naver Clova Ocr Success")
                .result(result)
                .build();

        return ResponseEntity.ok().body(baseRes);
    }
    // 이메일 찾기
    @RequestMapping(method = RequestMethod.POST, value = "/find/email")
    public ResponseEntity<BaseRes> findEmail(@RequestBody @Valid GetFindEmailUserReq getFindEmailUserReq) {
        BaseRes baseRes = userService.findEmail(getFindEmailUserReq);

        return ResponseEntity.ok().body(baseRes);
    }
    //등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
    @RequestMapping(method = RequestMethod.PATCH, value = "/find/password")
    public  ResponseEntity findPassword(@RequestBody @Valid PatchFindPasswordUserReq patchFindPasswordUserReq) throws MessagingException {
        sendEmailService.findPassword(patchFindPasswordUserReq.getEmail(),patchFindPasswordUserReq.getName());

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("비밀번호 찾기 요청 성공")
                .result("임시 비밀번호 메일 발송 완료")
                .build();

        return ResponseEntity.ok().body(baseRes);
    }
}
