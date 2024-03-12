package com.example.bootshelf.boardscrap.exception;

import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class BoardScrapException extends BusinessException {

    private ErrorCode errorCode;
    private String message;

    public BoardScrapException(ErrorCode errorCode, String message) {
        super(errorCode, message);
        this.errorCode = errorCode;
        this.message = message;
    }
}