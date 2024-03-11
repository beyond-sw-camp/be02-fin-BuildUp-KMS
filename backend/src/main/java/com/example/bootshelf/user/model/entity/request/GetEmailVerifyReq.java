package com.example.bootshelf.user.model.entity.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetEmailVerifyReq {
//
//    @NotNull(message = "이메일은 필수 입력 항목입니다.")
//    @Length(max=45, message = "이메일은 최대 45글자 이하여야 합니다.")
//    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "올바른 이메일 형식( test@example.com )으로 입력해주세요.")
//    @ApiModelProperty(value = "사용자 인증 이메일", example = "test@example.com", required = true)
    private String email;

//    @NotNull(message = "인증용 토큰은 필수 입력 항목입니다.")
//    @ApiModelProperty(value = "사용자 인증 토큰", example = "uuid", required = true)
    private String uuid;
}
