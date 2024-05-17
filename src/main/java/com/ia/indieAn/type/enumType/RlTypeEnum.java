package com.ia.indieAn.type.enumType;

import com.ia.indieAn.type.StrCodeValue;

public enum RlTypeEnum implements StrCodeValue {
    LEFT("L", "왼쪽"),
    RIGHT("R", "오른쪽");

    private String code;
    private String value;

    RlTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode(){ return code; }

    public String getValue() { return value; }
}
