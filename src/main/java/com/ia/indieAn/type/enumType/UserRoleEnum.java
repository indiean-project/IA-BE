package com.ia.indieAn.type.enumType;

import com.ia.indieAn.type.IntCodeValue;


public enum UserRoleEnum implements IntCodeValue {
    ADMIN(1, "관리자"),
    USER(2, "일반"),
    ARTIST(3, "아티스트");

    private int code;
    private String value;

    UserRoleEnum(int code, String value){
        this.code = code;
        this.value = value;
    }

    @Override
    public int getCode() {
        return code;
    }
    @Override
    public String getValue() {
        return value;
    }
}
