package com.ia.indieAn.type.enumType;

import com.ia.indieAn.type.StrCodeValue;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FundStatusEnum implements StrCodeValue {

    AWAIT("N", "승인대기"),
    APPROVAL("A", "승인"),
    REJECT("R", "반려");

    private final String code;
    private final String value;

    FundStatusEnum(String code, String value){
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

    private static final Map<String, FundStatusEnum> codes =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(FundStatusEnum::getCode, Function.identity())));

    public static String find(String code){
        return codes.get(code).getValue();
    }
}
