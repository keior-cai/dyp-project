package com.sise.common.execption;

import lombok.Data;

@Data
public class LoginException  extends RuntimeException {
    private int code = -999;

    private String message = "登录失败";
    public LoginException(){

    }
}
