package com.ia.indieAn.type.enumType;

import com.ia.indieAn.type.StrCodeValue;

public enum FabcTypeEnum implements StrCodeValue {
    FUND("F", "펀딩"),
    ARTIST("A", "아티스트"),
    BOARD("B", "게시글"),
    CONCERT("C", "콘서트");

    private String code;
    private String value;

    FabcTypeEnum(String code, String value){
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
