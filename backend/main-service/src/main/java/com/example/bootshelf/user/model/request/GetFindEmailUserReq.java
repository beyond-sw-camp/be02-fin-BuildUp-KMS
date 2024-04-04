package com.example.bootshelf.user.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetFindEmailUserReq {

    @NotNull(message = "이름은 필수 입력 항목입니다.")
    @Length(max=30, message = "이름은 최대 30글자 이하여야 합니다.")
    @ApiModelProperty(value = "이름", example = "홍길동", required = true)
    private String name;

    @NotNull(message = "닉네임은 필수 입력 항목입니다.")
    @Length(max=30, message = "닉네임은 최대 30글자 이하여야 합니다.")
    @ApiModelProperty(value = "닉네임", example = "주니어개발자", required = true)
    private String nickName;
}
