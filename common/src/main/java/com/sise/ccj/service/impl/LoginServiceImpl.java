package com.sise.ccj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.constant.MongoDbConstant;
import com.sise.ccj.constant.TimeConstant;
import com.sise.ccj.enums.note.ServerNote;
import com.sise.ccj.exception.ServerException;
import com.sise.ccj.pojo.common.DypUserConnection;
import com.sise.ccj.request.login.LoginRequest;
import com.sise.ccj.service.LoginService;
import com.sise.ccj.utils.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisUtil redisUtil;
    @Override
    public JSONObject handleLogin(LoginRequest param) {
        DypUserConnection dypUserConnection = mongoTemplate.findOne(Query.query(
                Criteria.where(DypUserConnection.Field.USER_NAME).is(param.getUserName())
                        .and(DypUserConnection.Field.PASSWORD).is(param.getPassword())),
                DypUserConnection.class,
                MongoDbConstant.DYP_USER_COLLECTION);
        if (dypUserConnection != null) {
            String token = UUID.randomUUID().toString();
            JSONObject json = Maps.of(CommonConstant.COOKIE_TOKEN, token);
            json.put(DypUserConnection.Field.ROLE, dypUserConnection.getRole());
            String key = CommonConstant.KEY_LOGIN_TOKEN.replace(CommonConstant.REPLACE_TOKEN, token);
            redisUtil.set(key, JSON.toJSONString(dypUserConnection), TimeConstant.SERVEN_DAY_SECOND);
            return json;
        }
        throw new ServerException(ServerNote.USER_NAME_PASSWORD_ERROR);
    }
}
