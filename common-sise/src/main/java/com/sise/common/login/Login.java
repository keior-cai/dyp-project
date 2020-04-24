package com.sise.common.login;

import com.sise.common.auth.SiseAuth;

public interface Login {
    SiseAuth login(String sno, String password);
}
