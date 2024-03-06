package com.example.bootshelf.common.error.exception;

import com.example.bootshelf.common.error.ErrorCode;

public class BusinessException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
