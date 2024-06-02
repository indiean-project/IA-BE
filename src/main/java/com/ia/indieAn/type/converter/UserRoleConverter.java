package com.ia.indieAn.type.converter;

import com.ia.indieAn.type.enumType.UserRoleEnum;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class UserRoleConverter implements AttributeConverter<UserRoleEnum,Integer> {



    @Override
    public Integer convertToDatabaseColumn(UserRoleEnum userRoleEnum) {
        return userRoleEnum.getCode();
    }

    @Override
    public UserRoleEnum convertToEntityAttribute(Integer dbData) {
        return EnumSet.allOf(UserRoleEnum.class).stream()
                .filter(e -> e.getCode() == (dbData))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
