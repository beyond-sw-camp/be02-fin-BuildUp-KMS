package com.example.bootshelf.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Base response")
public class BaseRes {

    @ApiModelProperty(value = "Is success flag", example = "true")
    private Boolean isSuccess;

    @ApiModelProperty(value = "Response message", example = "Success")
    private String message;

    @ApiModelProperty(value = "Result data", notes = "API 마다 반환되는 결과가 달라짐")
    private Object result;
}
