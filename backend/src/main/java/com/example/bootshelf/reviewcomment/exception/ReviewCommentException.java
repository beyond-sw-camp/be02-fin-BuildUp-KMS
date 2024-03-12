package com.example.bootshelf.reviewcomment.exception;

import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class ReviewCommentException extends BusinessException {

    private ErrorCode errorCode;
    private String message;

    public ReviewCommentException(ErrorCode errorCode, String message) {
        super(errorCode, message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
