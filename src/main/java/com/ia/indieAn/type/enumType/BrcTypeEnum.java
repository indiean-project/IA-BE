package com.ia.indieAn.type.enumType;

import com.ia.indieAn.type.StrCodeValue;

public enum BrcTypeEnum implements StrCodeValue {
    BOARD("B", "게시글"),
    REPLY("R", "댓글"),
    CONCERTREPLY("C", "콘서트댓글");

    private String code;
    private String value;

    BrcTypeEnum(String code, String value){
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode(){return code;}

    @Override
    public String getValue() {return value;}
}
