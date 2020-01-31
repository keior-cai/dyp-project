package com.sise.ccj.customer.controller;

import com.alibaba.fastjson.JSON;
import com.sise.ccj.annotation.AccessAuthority;
import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.constant.TimeConstant;
import com.sise.ccj.customer.config.CustomerConfig;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.pojo.admin.UserPO;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


/**
 * @author :  CCJ
 * @date : 2019/4/17 20:58
 * 微信授权
 */
@Controller
@RequestMapping(value = "/api/client/")
@Slf4j
public class WeChatLoginController {

    @Autowired
    private CustomerConfig customerConfig;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 微信登录重定向到微信短
     *
     * @return 回调我们服务端的地址
     */
    @GetMapping(value = "/weChatlogin")
    @AccessAuthority
    public String weChatRedirect() {
        String url = customerConfig.getUrl();
        log.info("wechatUrlConfig.getUrl()={}", url);
        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, null);
        return "redirect:" + redirectURL;
    }

    /**
     * 微信授权
     *
     * @param code 微信返回的 code 用于获取用户信息
     * @return 页面跳转
     */
    @GetMapping(value = "/login")
    @AccessAuthority
    public void redirectToIndexPage(@RequestParam("code") String code, HttpServletResponse response) throws WxErrorException, IOException {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        CustomerPO customerPO = new CustomerPO();
        customerPO.setOpenId(wxMpOAuth2AccessToken.getOpenId());
        String token = UUID.randomUUID().toString();
        customerPO.setToken(token);
        redisUtil.set(CommonConstant.KEY_LOGIN_TOKEN
                .replace(CommonConstant.REPLACE_TOKEN, token), JSON.toJSONString(customerPO),
                TimeConstant.SERVEN_DAY_MILLIS);
        Cookie cookie = new Cookie(CommonConstant.COOKIE_TOKEN, token);
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setContentType("application/json;charset=UTF-8");
        response.sendRedirect(customerConfig.getRedirectUrl());

        //返回需要跳转的页面锚点
    }

}