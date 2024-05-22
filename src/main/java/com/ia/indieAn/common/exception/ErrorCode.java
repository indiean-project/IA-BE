package com.ia.indieAn.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //유저 계정 관련
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "account-001", "유저 정보를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "account-002", "비밀번호가 일치하지 않습니다."),
    HAS_ID(HttpStatus.BAD_REQUEST, "account-003", "존재하는 아이디입니다."),
    HAS_NICKNAME(HttpStatus.BAD_REQUEST, "account-004", "존재하는 닉네임입니다."),
    HAS_PHONE(HttpStatus.BAD_REQUEST, "account-005", "존재하는 휴대폰번호입니다."),
    //페이지 관련
    INVALID_PAGE(HttpStatus.BAD_REQUEST, "account-006", "존재하지 않는 페이지입니다.");




    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
