package com.example.bootshelf.reviewup.exception;

import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class ReviewUpException extends BusinessException {

    private ErrorCode errorCode;
    private String message;

    public ReviewUpException(ErrorCode errorCode, String message) {
        super(errorCode, message);
        this.errorCode = errorCode;
        this.message = message;
    }
}