package com.sise.ccj.utils;

import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.constant.TimeConstant;
import com.sise.ccj.vo.LoginAccountInfo;
import org.springframework.util.StringUtils;

public class LoginAccountManager {

	public static LoginAccountInfo getLoginAccountInfo(String token, RedisUtil redisUtil) {
		if (StringUtils.isEmpty(token)) 
			return null;
		
		LoginAccountInfo accountInfo;

		String redisKey = CommonConstant.KEY_LOGIN_TOKEN.replace("${token}", token);
		Object value = redisUtil.get(redisKey);
		if (null == value) 
			return null;
		
		String loginInfo = value.toString();
		if (StringUtils.isEmpty(loginInfo)) 
			return null;
		
		accountInfo = JSONObject.parseObject(loginInfo, LoginAccountInfo.class);
		accountInfo.setBuildTimestamp(System.currentTimeMillis());

		return accountInfo;
	}

	public static void setLoginAccountInfo(LoginAccountInfo accountInfo, String loginToken, RedisUtil redisUtil) {
		String userInfo = JSONObject.toJSONString(accountInfo);
		String redisKey = CommonConstant.KEY_LOGIN_TOKEN.replace("${token}", loginToken);
		redisUtil.set(redisKey, userInfo, TimeConstant.SERVEN_DAY_SECOND);
	}
}
