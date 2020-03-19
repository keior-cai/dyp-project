package com.sise.ccj.customer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.constant.TimeConstant;
import com.sise.ccj.exception.ServerException;
import com.sise.ccj.mapper.CustomerMapper;
import com.sise.ccj.mapper.UserMapper;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip")
public class VipController {

    @Autowired
    private CustomerMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/open")
    public HttpBody open(@RequestBody JSONObject json){
        CustomerPO customerPO = SessionContextHolder.getAccountAndValid(null);
        String password = json.getString("password");
        if (StringUtils.isEmpty(password) || password.equals(customerPO.getPayPassword())){
            throw new ServerException("支付密码错误");
        }
        customerPO.setIsVip(1);
        userMapper.insertUpdate(customerPO);
        redisUtil.set(CommonConstant.KEY_LOGIN_TOKEN
                        .replace(CommonConstant.REPLACE_TOKEN, customerPO.getToken()), JSON.toJSONString(customerPO),
                TimeConstant.TOW_SECOND_MILLIS);
        return HttpBody.SUCCESS;
    }
}
