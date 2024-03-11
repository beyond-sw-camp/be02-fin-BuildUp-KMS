package com.example.bootshelf.user.exception;


import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.exception.EntityDuplicateException;

public class UserDuplicateException extends EntityDuplicateException {

    public UserDuplicateException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    // 이메일 중복
    public static UserDuplicateException forSignupEmail(String email) {
        return new UserDuplicateException(ErrorCode.DUPLICATE_SIGNUP_EMAIL, String.format("SignUp Email [ %s ] is duplicated.", email));
    }

    // 닉네임 중복
    public static UserDuplicateException forSignupUserNickName(String userNickName) {
        return new UserDuplicateException(ErrorCode.DUPLICATE_SIGNUP_NICKNAME, String.format("SignUp NickName [ %s ] is duplicated.", userNickName));
    }
}
