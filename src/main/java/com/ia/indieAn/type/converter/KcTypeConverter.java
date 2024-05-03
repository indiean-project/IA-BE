package com.ia.indieAn.type.converter;

import com.ia.indieAn.type.enumType.KcTypeEnum;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class KcTypeConverter implements AttributeConverter<KcTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(KcTypeEnum attribute) {
        return attribute.getCode();
    }

    @Override
    public KcTypeEnum convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(KcTypeEnum.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException());
    }
}
