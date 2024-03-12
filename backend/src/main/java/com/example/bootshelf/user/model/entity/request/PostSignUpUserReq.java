package com.example.bootshelf.user.model.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSignUpUserReq {

    @NotNull(message = "이메일은 필수 입력 항목입니다.")
    @Length(max=45, message = "이메일은 최대 45글자 이하여야 합니다.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "올바른 이메일 형식( test@example.com )으로 입력해주세요.")
    @ApiModelProperty(value = "이메일", example = "test@example.com", required = true)
    private String email;

    @NotNull(message = "패스워드는 필수 입력 항목입니다.")
    @Length(min=8, max=45 , message = "패스워드는 8글자 이상, 45글자 이하여야 합니다.")
    // @Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\\\",.<>/?]).{8,}$", message = "패스워드는 대/소문자, 특수문자, 숫자를 반드시 포함한 8글자 이상이어야 합니다.")
    @ApiModelProperty(value = "패스워드(최소 길이 : 8글자 / 적어도 한개의 대문자, 소문자, 특수문자, 숫자를 포함)", example = "Qwer1234@", required = true)
    private String password;

    @NotNull(message = "이름은 필수 입력 항목입니다.")
    @Length(max=30, message = "이름은 최대 30글자 이하여야 합니다.")
    @ApiModelProperty(value = "이름", example = "홍길동", required = true)
    private String name;

    @NotNull(message = "닉네임은 필수 입력 항목입니다.")
    @Length(max=30, message = "닉네임은 최대 30글자 이하여야 합니다.")
    @ApiModelProperty(value = "닉네임", example = "주니어개발자", required = true)
    private String nickName;

    @ApiModelProperty(value = "프로필 이미지", required = true)
    private String profileImage;

    //    @NotNull(message = "프로그램명은 필수 입력 항목입니다.")
    @Length(max=45, message = "프로그램명은 최대 45글자 이하여야 합니다.")
    @ApiModelProperty(value = "프로그램명", example = "한화시스템 BEYOND SW 캠프", required = true)
    private String programName;
}
