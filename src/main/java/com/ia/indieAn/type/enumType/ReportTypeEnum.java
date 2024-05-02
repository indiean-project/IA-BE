package com.ia.indieAn.type.enumType;

import com.ia.indieAn.type.StrCodeValue;

public enum ReportTypeEnum implements StrCodeValue {
    HEOWI("1", "허위사실유포"),
    MYUNGYE("2", "명예훼손"),
    YOK("3", "욕설"),
    AD("4", "광고"),
    GUITAR("5", "기타");

    private String code;
    private String value;

    ReportTypeEnum(String code, String value){
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
