package com.example.bootshelf.boardcomment.exception;

import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class BoardCommentException extends BusinessException {

    private ErrorCode errorCode;
    private String message;

    public BoardCommentException(ErrorCode errorCode, String message) {
        super(errorCode, message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
