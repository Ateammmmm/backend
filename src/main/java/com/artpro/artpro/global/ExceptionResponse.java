package com.artpro.artpro.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionResponse {

    private final String message;
    private final String code;
    private final int status;
}