package com.example.bootshelf.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseRes {

    private Boolean isSuccess;

    private String message;

    private Object result;
}
