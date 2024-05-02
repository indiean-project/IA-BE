package com.ia.indieAn.type.converter;

import com.ia.indieAn.type.enumType.FundTypeEnum;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class FundTypeConverter implements AttributeConverter<FundTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(FundTypeEnum attribute) {
        return attribute.getCode();
    }

    @Override
    public FundTypeEnum convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(FundTypeEnum.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()->new NoSuchElementException());
    }
}
