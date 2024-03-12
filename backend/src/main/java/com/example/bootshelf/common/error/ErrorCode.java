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
    EXPIRED_VERIFICATION_TOKEN(HttpStatus.UNAUTHORIZED, "ACCOUNT-004", "토큰의 유효기간이 만료된 경우"),

    // 회원
    DUPLICATE_SIGNUP_EMAIL(HttpStatus.BAD_REQUEST, "USER-001", "회원 이메일이 중복된 경우"),
    DUPLICATE_SIGNUP_NICKNAME(HttpStatus.BAD_REQUEST, "USER-002", "회원 닉네임이 중복된 경우"),
    USER_NOT_EXISTS(HttpStatus.NOT_FOUND, "USER-003", "회원을 찾을 수 없는 경우"),
    DIFFERENT_USER_PASSWORD(HttpStatus.BAD_REQUEST, "USER-004", "회원의 패스워드가 저장된 데이터와 다른 경우"),

    // 후기
    REVIEW_NOT_EXISTS(HttpStatus.NOT_FOUND, "REVIEW-001", "해당 후기 IDX 가 존재하지 않는 경우"),
    DUPLICATE_REVIEW_TITLE(HttpStatus.BAD_REQUEST, "REVIEW-002", "후기글의 제목이 중복된 경우"),


    // 후기 스크랩
    DUPLICATED_REVIEW_SCRAP(HttpStatus.CONFLICT, "REVIEWSCRAP-001", "이미 스크랩한 후기입니다."),

    REVIEW_SCRAP_IS_EMPTY(HttpStatus.NOT_FOUND, "REVIEWSCRAP-002", "스크랩한 후기가 존재하지 않습니다."),

    UNAUTHORIZED_REVIEW_SCRAP(HttpStatus.UNAUTHORIZED, "REVIEWSCRAP-003", "후기를 스크랩한 회원과 현재 회원이 일치하지 않습니다."),

    REVIEW_SCRAP_NOT_EXISTS(HttpStatus.NOT_FOUND, "REVIEWSCRAP-004", "해당 후기 스크랩 데이터가 존재하지 않습니다."),


    // 게시판
    BOARD_NOT_EXISTS(HttpStatus.NOT_FOUND, "BOARD-001", "해당 게시글 IDX 가 존재하지 않는 경우"),


    // 게시판 스크랩
    DUPLICATED_BOARD_SCRAP(HttpStatus.CONFLICT, "BOARDSCRAP-001", "이미 스크랩한 게시판입니다."),
    BOARD_SCRAP_IS_EMPTY(HttpStatus.NOT_FOUND, "BOARDSCRAP-002", "스크랩한 게시글이 존재하지 않습니다."),
    UNAUTHORIZED_BOARD_SCRAP(HttpStatus.UNAUTHORIZED, "BOARDSCRAP-003", "게시글을 스크랩한 회원과 현재 회원이 일치하지 않습니다."),
    BOARD_SCRAP_NOT_EXISTS(HttpStatus.NOT_FOUND, "BOARDSCRAP-004", "해당 후기 스크랩 데이터가 존재하지 않습니다."),


    ;

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
