package com.ia.indieAn.type.converter;

import com.ia.indieAn.type.enumType.ContentTypeEnum;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class ContentTypeConverter implements AttributeConverter<ContentTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(ContentTypeEnum attribute) {
        return attribute.getCode();
    }

    @Override
    public ContentTypeEnum convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(ContentTypeEnum.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException());
    }
}
