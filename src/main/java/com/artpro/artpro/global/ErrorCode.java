package com.artpro.artpro.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "ACCOUNT-001", "입력값이 올바르지 않습니다."),
    EXITING_EMAIL(409, "ACCOUNT-002", "이미 등록되어 있는 이메일입니다."),
    EXITING_NICKNAME(409, "ACCOUNT-003", "이미 존재하는 닉네임입니다."),
    MISMATCH_PASSWORD(409, "ACCOUNT-004", "패스워드가 일치하지 않습니다.");

    private final int status;
    private final String code;
    private final String message;
}
