package com.sise.ccj.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.admin.config.AdminConfig;
import com.sise.ccj.annotation.AccessAuthority;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.request.login.LoginRequest;
import com.sise.ccj.service.LoginService;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@AccessAuthority
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AdminConfig adminConfig;

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest param, HttpServletResponse response) throws IOException {
        JSONObject json = loginService.handleLogin(param);
        Cookie cookie = new Cookie(CommonConstant.COOKIE_TOKEN, json.getString(CommonConstant.COOKIE_TOKEN));
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.getWriter().println(JSON.toJSONString(HttpBody.getSucInstance(json)));
    }
    @GetMapping("/logout")
    public void logout(HttpServletResponse response) throws IOException {
        response.addCookie(new Cookie(CommonConstant.COOKIE_TOKEN, null));
        response.sendRedirect(adminConfig.getLoginPath());
    }
}
