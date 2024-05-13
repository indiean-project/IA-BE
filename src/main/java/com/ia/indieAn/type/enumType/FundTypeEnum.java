package com.ia.indieAn.type.enumType;

import com.ia.indieAn.type.StrCodeValue;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FundTypeEnum implements StrCodeValue {
    CONCERT("1", "공연"),
    ALBUM("2", "앨범"),
    GOODS("3", "굿즈"),
    FANMEETING("4", "팬미팅");

    private final String code;
    private final String value;

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

    private static final Map<String, FundTypeEnum> codes =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(FundTypeEnum::getCode, Function.identity())));

    public static String find(String code){
        return codes.get(code).getValue();
    }
}
