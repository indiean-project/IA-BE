package com.ia.indieAn.type.converter;

import com.ia.indieAn.type.enumType.ArtistRoleStatusTypeEnum;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class ArtistRoleStatusTypeConverter implements AttributeConverter<ArtistRoleStatusTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(ArtistRoleStatusTypeEnum attribute) {
        return attribute.getCode();
    }

    @Override
    public ArtistRoleStatusTypeEnum convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(ArtistRoleStatusTypeEnum.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException());
    }
}





