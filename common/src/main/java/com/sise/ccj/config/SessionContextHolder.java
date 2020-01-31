package com.sise.ccj.config;

import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.exception.ServerException;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.pojo.admin.UserPO;


public class SessionContextHolder {

    private static ThreadLocal<JSONObject> context = ThreadLocal.withInitial(JSONObject :: new);
  
    public static void setToken(String token) {
    	context.get().put(CommonConstant.COOKIE_TOKEN, token);
    }

    public static void setLoginAccountInfo(UserPO userPO) {
        context.get().put("loginAccountInfo", userPO);
    }

    public static void setLoginAccountInfo(CustomerPO userPO) {
        context.get().put("loginAccountInfo", userPO);
    }

    public static UserPO getLoginAccountInfo() {
        return context.get().getObject("loginAccountInfo",UserPO.class);
    }

    public static CustomerPO getLoginAccountInfo(String type) {
        return context.get().getObject("loginAccountInfo",CustomerPO.class);
    }
    
    public static UserPO getAccountAndValid() {
        UserPO accountInfo = getLoginAccountInfo();
    	if (null == accountInfo) {
    		throw new ServerException("非法登录");
    	}
    	return accountInfo;
    }
    public static CustomerPO getAccountAndValid(String type) {
        CustomerPO accountInfo = getLoginAccountInfo(type);
        if (null == accountInfo) {
            throw new ServerException("非法登录");
        }
        return accountInfo;
    }
}
