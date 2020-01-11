package com.sise.ccj.service;

import com.sise.ccj.vo.LoginAccountInfo;
import com.sise.ccj.vo.LoginVo;

import java.awt.image.BufferedImage;

public interface ILoginValidService {

	/**
	 * 登出功能：移除登陆状态
	 * @param accountInfo
	 */
	void loginout(LoginAccountInfo accountInfo, String cookie);
	
	/**
	 * 校验登陆逻辑
	 * @param loginVo
	 * @return token 返回登陆成功token信息
	 */
	String handleLoginin(LoginVo loginVo) throws Exception;
	
	/**
	 * 获取登陆验证码
	 * @param seqNo
	 * @return
	 */
	BufferedImage getVerifyCode(String seqNo);

	void validateUserExist(LoginAccountInfo accountInfo);
	
}
