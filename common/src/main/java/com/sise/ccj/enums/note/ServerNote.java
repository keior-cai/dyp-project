package com.sise.ccj.enums.note;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServerNote {

    USER_NAME_PASSWORD_ERROR(19100001, "用户名或密码错误!"),
    ;

    private int code;

    private String message;
}
