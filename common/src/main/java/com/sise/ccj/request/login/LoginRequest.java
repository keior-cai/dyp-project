package com.sise.ccj.request.login;

import com.sise.ccj.enums.note.ServerNote;
import com.sise.ccj.exception.ServerException;
import lombok.Data;

@Data
public class LoginRequest {

    private String userName;

    private String password;

    private String verifyCode;

    private String mobileVerifyCode;

    public void check(){
        if (this.userName == null || this.password == null) {
            throw new ServerException(ServerNote.USER_NAME_PASSWORD_ERROR);
        }
    }
}
