package com.ia.indieAn.type.converter;

import com.ia.indieAn.type.enumType.BrcTypeEnum;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class BrTypeConverter implements AttributeConverter<BrcTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(BrcTypeEnum attribute) {
        return attribute.getCode();
    }

    @Override
    public BrcTypeEnum convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(BrcTypeEnum.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException());
    }
}
