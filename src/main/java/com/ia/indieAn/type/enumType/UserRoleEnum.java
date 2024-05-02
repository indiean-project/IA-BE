package com.ia.indieAn.type.enumType;

import com.ia.indieAn.type.StrCodeValue;

public enum UserRoleEnum implements StrCodeValue {
    ADMIN("1", "관리자"),
    USER("2", "일반"),
    ARTIST("3", "아티스트");

    private String code;
    private String value;

    UserRoleEnum(String code, String value){
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }
    @Override
    public String getValue() {
        return value;
    }
}
