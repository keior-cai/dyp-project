package com.sise.ccj.task.config;

import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.TimeConstant;

public class TaskSlector {
    public static boolean isMaster;



    private TaskSlector(){}


    public boolean selectorMaster(String env, RedisUtil redisUtil){
        boolean setting = redisUtil.setnx(Constant.MASTER_ENV, env, TimeConstant.FIVE_MINUTE_SECOND);
        if (setting){
            isMaster = true;
        }else {
            isMaster = false;
        }
        return isMaster;
    }
}
