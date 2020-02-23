package com.sise.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TestService {

    @Autowired
    private SiseLogin siseLogin;

    @PostMapping("/login")
    public void test(@RequestBody JSONObject param){
        String username = param.getString("username");
        String password = param.getString("password");
        SiseUserInfo siseUserInfo = siseLogin.login(username, password);
        System.out.println(siseUserInfo);
    }
}
