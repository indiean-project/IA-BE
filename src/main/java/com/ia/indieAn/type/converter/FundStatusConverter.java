package com.ia.indieAn.type.converter;

import com.ia.indieAn.type.enumType.FundStatusEnum;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class FundStatusConverter implements AttributeConverter<FundStatusEnum, String> {

    @Override
    public String convertToDatabaseColumn(FundStatusEnum attribute) {
        return attribute.getCode();
    }

    @Override
    public FundStatusEnum convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(FundStatusEnum.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
