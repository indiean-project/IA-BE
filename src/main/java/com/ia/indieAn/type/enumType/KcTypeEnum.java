package com.ia.indieAn.type.enumType;

import com.ia.indieAn.type.StrCodeValue;

public enum KcTypeEnum implements StrCodeValue {
    KING("K", "대표사진"),
    CONTENT("C", "본문사진");

    private String code;
    private String value;

    KcTypeEnum(String code, String value){
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
