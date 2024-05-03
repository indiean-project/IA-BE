package com.ia.indieAn.type.converter;

import com.ia.indieAn.type.enumType.BrTypeEnum;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class BrTypeConverter implements AttributeConverter<BrTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(BrTypeEnum attribute) {
        return attribute.getCode();
    }

    @Override
    public BrTypeEnum convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(BrTypeEnum.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException());
    }
}
