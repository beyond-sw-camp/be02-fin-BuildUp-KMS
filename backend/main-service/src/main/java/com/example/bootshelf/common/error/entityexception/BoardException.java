package com.example.bootshelf.common.error.entityexception;

import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class BoardException extends BusinessException {

    private ErrorCode errorCode;
    private String message;

    public BoardException(ErrorCode errorCode, String message) {
        super(errorCode, message);
        this.errorCode = errorCode;
        this.message = message;
    }
}