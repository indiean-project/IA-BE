package com.ia.indieAn.type.converter;

import com.ia.indieAn.type.enumType.UserRoleEnum;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class UserRoleConverter implements AttributeConverter<UserRoleEnum, String> {

    @Override
    public UserRoleEnum convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(UserRoleEnum.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException());
    }

    @Override
    public String convertToDatabaseColumn(UserRoleEnum userRoleEnum) {
        return userRoleEnum.getCode();
    }
}
