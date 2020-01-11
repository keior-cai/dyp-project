package com.sise.ccj.constant;

public interface CommonConstant {
	
	String Separtor = "-";

	/**
	 * yicall登陆token-session信息存储
	 */
	String KEY_LOGIN_TOKEN = "dy:wt:tmp:lt:private:${token}";
	
	/**
	 * zx静默坐席私有登陆的token信息
	 */
	String COOKIE_TOKEN = "zx-token";
	
	String KEY_REQUEST_IP_FAIL_COUNT = "dy:wt:tmp:rip:failCount:private:${ip}";
	/**
	 * 预登陆的seqNo-session信息存储
	 */
	String KEY_PRE_LOGIN_SEQ = "dy:wt:tmp:plt:private:${seqNo}";
	
	String PRE_VERIFY_CODE = "DY_PRE_VERIFY_CODE";
	
	String KEY_REQUEST_IP_VALID = "dy:wt:tmp:rip:failCount:private:${nowTime}:${ip}:${username}";

	String KEY_ACCOUNT_TOKEN = "dy:wt:tmp:account:token:private";
}
