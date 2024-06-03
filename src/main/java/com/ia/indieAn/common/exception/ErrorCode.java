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
    //아티스트 관련
    ARTIST_NOT_FOUND(HttpStatus.BAD_REQUEST, "artist-001", "존재하지 않는 페이지입니다."),
    //펀딩 관련
    FUND_NOT_FOUND(HttpStatus.NOT_FOUND, "fund-001", "존재하는 펀딩이 없습니다."),
    //콘서트 관련
    CONCERT_NOT_FOUND(HttpStatus.NOT_FOUND, "concert-001", "존재하는 콘서트가 없습니다."),
    // 신고관련
    CONTENT_REPORT_NOT_FOUND(HttpStatus.NOT_FOUND, "content_report_log-001", "존재하는 신고내역이 없습니다."),

    // 게시글 관련
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "board-001", "존재하는 게시글이 없습니다."),

    QUESTION_NULL(HttpStatus.BAD_REQUEST, "question-001", "문의 내용이 없습니다."),

    COLO_BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "board-002", "잘못된 접근입니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
