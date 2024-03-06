package com.example.bootshelf.common.error.exception;


import com.example.bootshelf.common.error.ErrorCode;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
