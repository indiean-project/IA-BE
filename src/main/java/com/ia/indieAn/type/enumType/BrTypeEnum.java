package com.ia.indieAn.type.enumType;

import com.ia.indieAn.type.StrCodeValue;

public enum BrTypeEnum implements StrCodeValue {
    BOARD("B", "게시글"),
    REPLY("R", "댓글");

    private String code;
    private String value;

    BrTypeEnum(String code, String value){
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode(){return code;}

    @Override
    public String getValue() {return value;}
}
