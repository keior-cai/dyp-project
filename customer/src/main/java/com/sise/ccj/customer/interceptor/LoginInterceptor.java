package com.sise.ccj.customer.interceptor;

import com.sise.ccj.annotation.AccessAuthority;
import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.customer.config.CustomerConfig;
import com.sise.ccj.exception.ServerException;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.vo.HttpBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private CustomerConfig customerConfig;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getRemoteAddr();
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        if (handler instanceof ParameterizableViewController) {
            writeHtmlRedirect(response, customerConfig.getIndexPath());
            return true;
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            if (method.hasMethodAnnotation(AccessAuthority.class) ||
                    method.getBeanType().isAnnotationPresent(AccessAuthority.class)) {
                AccessAuthority access = method.getMethodAnnotation(AccessAuthority.class);
                if (access == null) {
                    access = method.getBeanType().getAnnotation(AccessAuthority.class);
                }
                if (AccessAuthority.PUBLIC.equalsIgnoreCase(access.value())) {
                    return true;
                }
            }
            String token = getToken(request);
            CustomerPO admin = getUserInfo(token);
            if (admin == null) {
                throw new ServerException(HttpBody.ERROR_CODE, "无效凭证", customerConfig.getLoginPath());
            }
            admin.setIp(ip);
            SessionContextHolder.setToken(token);
            SessionContextHolder.setLoginAccountInfo(admin);
        }
        return true;
    }

    private String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length <= 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(CommonConstant.COOKIE_TOKEN)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private void writeHtmlRedirect(HttpServletResponse response, String url) {
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    private CustomerPO getUserInfo(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String loginKey = CommonConstant.KEY_LOGIN_TOKEN
                .replace(CommonConstant.REPLACE_TOKEN, token);
        return redisUtil.get(loginKey, CustomerPO.class);
    }

}
