package com.sise.ccj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.constant.TimeConstant;
import com.sise.ccj.enums.admin.AdminRoleEnums;
import com.sise.ccj.enums.admin.AdminStatus;
import com.sise.ccj.enums.note.ServerNote;
import com.sise.ccj.exception.ServerException;
import com.sise.ccj.mapper.LogMapper;
import com.sise.ccj.mapper.UserMapper;
import com.sise.ccj.pojo.admin.LogPO;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.request.login.LoginRequest;
import com.sise.ccj.service.LoginService;
import com.sise.ccj.utils.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private LogMapper logMapper;

    @Override
    public JSONObject handleLogin(LoginRequest param, String ip) {
        UserPO userPO = userMapper.queryUserByNameAndPassword(param.getUserName(), param.getPassword());
        if (userPO != null && userPO.getStatus().equals(AdminStatus.ACTIVE.toString())) {
            String token = UUID.randomUUID().toString();
            userPO.setTableSpace(CommonConstant.TABLE_SPACE.replace(CommonConstant.TABLE_SPACE_ID, userPO.getId() + ""));
            JSONObject json = Maps.of(CommonConstant.COOKIE_TOKEN, token);
            String key = CommonConstant.KEY_LOGIN_TOKEN.replace(CommonConstant.REPLACE_TOKEN, token);
            userPO.setToken(token);
            redisUtil.set(key, JSON.toJSONString(userPO), TimeConstant.SERVEN_DAY_SECOND);
            userPO.setUpdateTime(new Date());
            userPO.setIp(ip);
            userMapper.updateUser(userPO);
            SessionContextHolder.setLoginAccountInfo(userPO);
            if (userPO.getRole() != AdminRoleEnums.SUPER_ADMIN.getRole()){
                logMapper.insertLog(LogPO.builder("登录成功", userPO.getId(), userPO.getIp(), userPO.getTableSpace()));
            }
            return json;
        }
        throw new ServerException(ServerNote.USER_NAME_PASSWORD_ERROR);
    }
}
