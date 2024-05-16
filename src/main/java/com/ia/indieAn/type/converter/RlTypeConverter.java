package com.ia.indieAn.type.converter;

import com.ia.indieAn.type.enumType.RlTypeEnum;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class RlTypeConverter implements AttributeConverter<RlTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(RlTypeEnum attribute) { return attribute.getCode(); }

    @Override
    public RlTypeEnum convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(RlTypeEnum.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException());
    }
}
