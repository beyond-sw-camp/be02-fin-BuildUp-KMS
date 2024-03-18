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
    DUPLICATE_USER_NICKNAME(HttpStatus.BAD_REQUEST, "USER-002", "회원 닉네임이 중복된 경우"),
    USER_NOT_EXISTS(HttpStatus.NOT_FOUND, "USER-003", "회원을 찾을 수 없는 경우"),
    DIFFERENT_USER_PASSWORD(HttpStatus.BAD_REQUEST, "USER-004", "회원의 패스워드가 저장된 데이터와 다른 경우"),

    // 관리자
    ADMIN_SIGNUP_EMAIL(HttpStatus.BAD_REQUEST, "ADMIN-001", "관리자 이메일이 중복된 경우"),
    ADMIN_NOT_EXISTS(HttpStatus.NOT_FOUND, "ADMIN-002", "관리자 계정을 찾을 수 없는 경우"),
    DIFFERENT_ADMIN_PASSWORD(HttpStatus.BAD_REQUEST, "ADMIN-003", "관리자의 패스워드가 저장된 데이터와 다른 경우"),

    // 교육과정
    COURSE_NOT_EXISTS(HttpStatus.NOT_FOUND, "COURSE-001", "과정명을 찾을 수 없는 경우"),

    // 후기
    REVIEW_NOT_EXISTS(HttpStatus.NOT_FOUND, "REVIEW-001", "해당 후기 IDX 가 존재하지 않는 경우"),
    DUPLICATE_REVIEW_TITLE(HttpStatus.BAD_REQUEST, "REVIEW-002", "후기글의 제목이 중복된 경우"),
    NO_SEARCH_TERMS(HttpStatus.BAD_REQUEST, "REVIEW-003", "후기글 검색 시 검색어를 입력하지 않은 경우"),

    // 후기 스크랩
    DUPLICATED_REVIEW_SCRAP(HttpStatus.CONFLICT, "REVIEWSCRAP-001", "이미 스크랩한 후기입니다."),

    REVIEW_SCRAP_IS_EMPTY(HttpStatus.NOT_FOUND, "REVIEWSCRAP-002", "스크랩한 후기가 존재하지 않습니다."),

    UNAUTHORIZED_REVIEW_SCRAP(HttpStatus.UNAUTHORIZED, "REVIEWSCRAP-003", "후기를 스크랩한 회원과 현재 회원이 일치하지 않습니다."),

    REVIEW_SCRAP_NOT_EXISTS(HttpStatus.NOT_FOUND, "REVIEWSCRAP-004", "해당 후기 스크랩 데이터가 존재하지 않습니다."),
  

    // 후기 추천
    DUPLICATED_REVIEW_UP(HttpStatus.CONFLICT, "REVIEWUP-001", "이미 추천한 후기입니다."),
    REVIEW_UP_IS_EMPTY(HttpStatus.NOT_FOUND, "REVIEWUP-002", "추천한 후기가 존재하지 않습니다."),
    UNAUTHORIZED_REVIEW_UP(HttpStatus.UNAUTHORIZED, "REVIEWUP-003", "후기를 추천한 회원과 현재 회원이 일치하지 않습니다."),

    REVIEW_UP_NOT_EXISTS(HttpStatus.NOT_FOUND, "REVIEWUP-004", "해당 후기 추천 데이터가 존재하지 않습니다."),


    // 게시글 댓글 추천
    DUPLICATED_REVIEW_COMMENT_UP(HttpStatus.CONFLICT, "REVIEWUP-001", "이미 추천한 후기입니다."),
    REVIEW_COMMENT_UP_IS_EMPTY(HttpStatus.NOT_FOUND, "BOARDSCRAP-002", "스크랩한 게시글이 존재하지 않습니다."),
    UNAUTHORIZED_REVIEW_COMMENT_UP(HttpStatus.UNAUTHORIZED, "REVIEWUP-003", "후기를 추천한 회원과 현재 회원이 일치하지 않습니다."),
    REVIEW_COMMENT_UP_NOT_EXISTS(HttpStatus.NOT_FOUND, "REVIEWUP-004", "해당 후기 추천 데이터가 존재하지 않습니다."),


    // 게시판
    BOARD_NOT_EXISTS(HttpStatus.NOT_FOUND, "BOARD-001", "해당 게시글 IDX 가 존재하지 않는 경우"),

    DUPICATED_BOARD_TITLE(HttpStatus.CONFLICT, "BOARD-002", "이미 존재하는 게시글 제목입니다"),
  

    // 게시판 스크랩
    DUPLICATED_BOARD_SCRAP(HttpStatus.CONFLICT, "BOARDSCRAP-001", "이미 스크랩한 게시판입니다."),
    BOARD_SCRAP_IS_EMPTY(HttpStatus.NOT_FOUND, "BOARDSCRAP-002", "스크랩한 게시글이 존재하지 않습니다."),
    UNAUTHORIZED_BOARD_SCRAP(HttpStatus.UNAUTHORIZED, "BOARDSCRAP-003", "게시글을 스크랩한 회원과 현재 회원이 일치하지 않습니다."),
    BOARD_SCRAP_NOT_EXISTS(HttpStatus.NOT_FOUND, "BOARDSCRAP-004", "해당 후기 스크랩 데이터가 존재하지 않습니다."),
  

    // 댓글
    REVIEW_COMMENT_NOT_EXISTS(HttpStatus.NOT_FOUND, "REVIEW-COMMENT-001", "해당 댓글 IDX 가 존재하지 않는 경우"),
    BOARD_COMMENT_NOT_EXISTS(HttpStatus.NOT_FOUND, "REVIEW-COMMENT-001", "해당 댓글 IDX 가 존재하지 않는 경우"),


    // 게시글 댓글 추천
    DUPLICATED_BOARD_COMMENT_UP(HttpStatus.CONFLICT, "REVIEWUP-001", "이미 추천한 후기입니다."),
    BOARD_COMMENT_UP_IS_EMPTY(HttpStatus.NOT_FOUND, "BOARDSCRAP-002", "스크랩한 게시글이 존재하지 않습니다."),
    UNAUTHORIZED_BOARD_COMMENT_UP(HttpStatus.UNAUTHORIZED, "REVIEWUP-003", "후기를 추천한 회원과 현재 회원이 일치하지 않습니다."),
    BOARD_COMMENT_UP_NOT_EXISTS(HttpStatus.NOT_FOUND, "REVIEWUP-004", "해당 후기 추천 데이터가 존재하지 않습니다."),


    // 태그
    TAG_NOT_EXISTS(HttpStatus.NOT_FOUND, "TAG-001", "해당 태그 IDX가 존재하지 않는 경우"),
    DUPLICATED_TAG_NAME(HttpStatus.BAD_REQUEST, "TAG-002", "태그명이 중복되는 경우"),

    // 게시판 태그
    BOARD_TAG_NOT_EXISTS(HttpStatus.NOT_FOUND, "BOARD_TAG-001", "해당 게시판 태그 IDX가 존재하지 않는 경우"),

    // 게시판 추천
    DUPLICATED_BOARD_UP(HttpStatus.CONFLICT, "BOARDUP-001", "이미 추천한 게시판입니다."),
    BOARD_UP_IS_EMPTY(HttpStatus.NOT_FOUND, "BOARDUP-002", "추천한 게시글이 존재하지 않습니다."),
    UNAUTHORIZED_BOARD_UP(HttpStatus.UNAUTHORIZED, "BOARDUP-003", "게시글을 추천한 회원과 현재 회원이 일치하지 않습니다."),

    BOARD_UP_NOT_EXISTS(HttpStatus.NOT_FOUND, "BOARDUP-004", "해당 게시글 추천 데이터가 존재하지 않습니다."),

    // 관리자 게시판 태그 등록
    BOARD_CATEGORY_NOT_EXISTS(HttpStatus.NOT_FOUND, "BOARD-CATEGORY-001", "해당 게시판 카테고리 IDX가 존재하지 않는 경우"),

    // 네이버 OCR
    OCR_TEXT_NOT_FOUND(HttpStatus.BAD_REQUEST, "OCR-001", "OCR 판독 결과 아무런 글자도 나오지 않은 경우")
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
