package com.ia.indieAn.type.enumType;

import com.ia.indieAn.type.StrCodeValue;

public enum FundTypeEnum implements StrCodeValue {
    CONCERT("1", "공연"),
    ALBUM("2", "앨범"),
    GOODS("3", "굿즈"),
    FANMEETING("4", "팬미팅");

    private String code;
    private String value;

    FundTypeEnum(String code, String value){
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
