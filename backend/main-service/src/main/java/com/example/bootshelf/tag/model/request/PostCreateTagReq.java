package com.example.bootshelf.tag.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateTagReq {

    @NotNull(message = "태그명은 필수 입력 항목입니다.")
    @Length(min=1, max=45, message = "태그명은 최소 1글자 이상, 최대 45자 이하여야 합니다.")
    @ApiModelProperty(value = "태그명( 45자 이하 )", example = "백엔드", required = true)
    private String tagName;

}
