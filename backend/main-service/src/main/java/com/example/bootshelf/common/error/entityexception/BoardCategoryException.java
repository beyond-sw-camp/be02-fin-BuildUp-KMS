package com.example.bootshelf.common.error.entityexception;

import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.exception.BusinessException;

public class BoardCategoryException extends BusinessException {
    private ErrorCode errorCode;
    private String message;

    public BoardCategoryException(ErrorCode errorCode, String message) {
        super(errorCode, message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
