package com.ia.indieAn.type.enumType;

import com.ia.indieAn.type.StrCodeValue;

public enum ArtistRoleStatusTypeEnum implements StrCodeValue {

    PERMISSION( "Y", "아티스트"),
    REFUSE("N", "일반유저");

    private String code;
    private String value;

    ArtistRoleStatusTypeEnum(String code, String value) {

        this.code = code;
        this.value = value;
    }


    @Override
    public String getCode() {return code;}
    @Override
    public String getValue() {
        return value;
    }
}

