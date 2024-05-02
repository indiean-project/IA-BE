package com.ia.indieAn.type.enumType;

import com.ia.indieAn.type.StrCodeValue;

public enum ContentTypeEnum implements StrCodeValue {
    FREE("1", "자유게시판"),
    PROUD("2", "자랑게시판"),
    COLO("3", "콜로세움"),
    ONLY("4", "전용게시판");

    private String code;
    private String value;

    ContentTypeEnum(String code, String value){
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
