package com.sise.ccj.service;

import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.request.login.LoginRequest;

public interface LoginService {

    JSONObject handleLogin(LoginRequest param);
}
