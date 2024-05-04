package com.ia.indieAn.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    ErrorCode errorCode;
}
