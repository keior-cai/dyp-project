package com.sise.ccj.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.admin.config.AdminConfig;
import com.sise.ccj.annotation.AccessAuthority;
import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.enums.admin.AdminRoleEnums;
import com.sise.ccj.mapper.LogMapper;
import com.sise.ccj.pojo.admin.LogPO;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.request.login.LoginRequest;
import com.sise.ccj.service.LoginService;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private LogMapper logMapper;

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest param, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String ip = request.getRemoteAddr();
        JSONObject json = loginService.handleLogin(param, ip);
        Cookie cookie = new Cookie(CommonConstant.COOKIE_TOKEN, json.getString(CommonConstant.COOKIE_TOKEN));
        cookie.setPath("/");
        response.addCookie(cookie);
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        if (userPO.getRole() == AdminRoleEnums.SUPER_ADMIN.getRole()){
            json.put("url", adminConfig.getSupperAdminPath());
        } else {
            json.put("url", adminConfig.getAdminPath());
        }
        response.getWriter().println(JSON.toJSONString(HttpBody.getSucInstance(json)));
    }
    @GetMapping("/logout")
    @AccessAuthority
    public void logout(HttpServletResponse response) throws IOException {
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        response.addCookie(new Cookie(CommonConstant.COOKIE_TOKEN, null));
        logMapper.insertLog(LogPO.builder("登出成功", userPO.getId(), userPO.getIp(), userPO.getTableSpace()));
    }
}
