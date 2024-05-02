package com.ia.indieAn.type.converter;

import com.ia.indieAn.type.enumType.ReportTypeEnum;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class ReportTypeConverter implements AttributeConverter<ReportTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(ReportTypeEnum attribute) {
        return attribute.getCode();
    }

    @Override
    public ReportTypeEnum convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(ReportTypeEnum.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException());
    }
}
