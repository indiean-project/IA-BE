package com.ia.indieAn.common.responseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    SUCCESS("요청 처리 완료"),
    FAIL("요청 처리 실패");

    private final String value;
}
