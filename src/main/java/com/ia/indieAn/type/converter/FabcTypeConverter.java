package com.ia.indieAn.type.converter;

import com.ia.indieAn.type.enumType.FabcTypeEnum;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class FabcTypeConverter implements AttributeConverter<FabcTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(FabcTypeEnum attribute) {
        return attribute.getCode();
    }

    @Override
    public FabcTypeEnum convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(FabcTypeEnum.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException());
    }
}
