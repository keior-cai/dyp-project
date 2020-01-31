package com.sise.ccj.admin.interceptor;

import com.alibaba.fastjson.JSON;
import com.sise.ccj.admin.config.AdminConfig;
import com.sise.ccj.annotation.AccessAuthority;
import com.sise.ccj.annotation.AccessRolePermission;
import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.enums.admin.AdminRoleEnums;
import com.sise.ccj.exception.ServerException;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.vo.HttpBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
    private AdminConfig adminConfig;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getRemoteAddr();
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        if (handler instanceof ParameterizableViewController) {
            writeHtmlRedirect(response, adminConfig.getIndexPath());
            return true;
        }
        if (handler instanceof  HandlerMethod) {
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
            UserPO admin = getUserInfo(token);
            if (admin == null) {
                HttpBody body = HttpBody.getInstance(HttpBody.NOTE_CODE, "无效凭证");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(JSON.toJSONString(body));
                return false;
            }
            admin.setIp(ip);
            SessionContextHolder.setToken(token);
            SessionContextHolder.setLoginAccountInfo(admin);
            if ((method.hasMethodAnnotation(AccessRolePermission.class) ||
                    method.getBeanType().isAnnotationPresent(AccessRolePermission.class)) &&
                    !StringUtils.isEmpty(token)) {
                admin = getUserInfo(token);
                if (admin.getRole() != null && AdminRoleEnums.GENERAL_ADMIN.getRole() == admin.getRole()) {
                    // 权限不足
                    HttpBody body = HttpBody.getInstance(HttpBody.NOTE_CODE, "权限不足");
                    response.addHeader("Content-Type","application/json;charset=UTF-8");
                    response.getWriter().println(JSON.toJSONString(body));
                    return false;
                }
            }
        }
        return true;
    }

    private String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length <= 0) {
            throw new ServerException("请求不合法");
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

    private UserPO getUserInfo(String token) {
        String loginKey = CommonConstant.KEY_LOGIN_TOKEN.replace(CommonConstant.REPLACE_TOKEN, token);
        return redisUtil.get(loginKey, UserPO.class);
    }

}
