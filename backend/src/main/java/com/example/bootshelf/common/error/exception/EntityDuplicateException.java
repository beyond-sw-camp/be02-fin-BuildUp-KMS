package com.example.bootshelf.common.error.exception;


import com.example.bootshelf.common.error.ErrorCode;

public class EntityDuplicateException extends BusinessException{

    public EntityDuplicateException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
