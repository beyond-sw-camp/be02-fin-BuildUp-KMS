package com.example.bootshelf.common.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 공통
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "COMMON-001", "유효성 검증에 실패한 경우"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON-002", "서버에서 처리할 수 없는 경우"),
    CONSTRAINT_VIOLATION(HttpStatus.BAD_REQUEST, "COMMON-003", "테이블 컬럼의 제약조건을 위반 한 경우"),

    // 계정
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "ACCOUNT-001", "인증에 실패한 경우"),
    TOKEN_NOT_EXISTS(HttpStatus.UNAUTHORIZED, "ACCOUNT-002", "토큰이 존재하지 않는 경우"),
    INVALID_VERIFICATION_TOKEN(HttpStatus.UNAUTHORIZED, "ACCOUNT-003", "토큰이 유효하지 않은 경우"),
    EXPIRED_VERIFICATION_TOKEN(HttpStatus.UNAUTHORIZED, "ACCOUNT-004", "토큰의 유효기간이 만료된 경우");



    private final HttpStatus status;  // 헤더로 반환할 Http 상태 코드
    private final String code;    // 페이로드로 반환할 에러 코드
    private final String description;  // 에러 코드 문서화를 위한 설명

    ErrorCode(HttpStatus status, String code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }

    public HttpStatus getStatus() { return status; }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
