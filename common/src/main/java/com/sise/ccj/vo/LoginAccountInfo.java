package com.sise.ccj.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginAccountInfo {
	public static final int TOKEN_INVALID = -1;
	public static final int CALL_CAN_USE = 1;
	public static final int CALL_NOT_INIT_SUCCESS = 2;
	public static final int CALL_NOT_ACTIVE = 3;


	private int accountStatus;

	private String socketId;

	private String token;

	private String userName;

	/**
	 * 昵称
	 */
	private String nickName;

	private String avatar;

	private int userId;
	/**
	 * 角色
	 */
	private int role;

	private int autoPlay;

	private double playSpeed;

	private double playVolume;

	private int maxLoad;

	private long buildTimestamp;

	private double rate;

	private String fileName;

	private Boolean downloadStatus = false;
	//权限信息等
}
