package com.sise.ccj.utils;

import com.sise.ccj.compant.SpringContext;
import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.RedisConstant;

import java.util.Date;

/**
 * @ClassName OrderSnUtils
 * @Description
 * @Author CCJ
 * @Date 2020/2/5 16:27
 **/
public class OrderSnUtils {
    private OrderSnUtils(){}

    public static String createOrderSn(){
        RedisUtil redisUtil = SpringContext.getBeanByType(RedisUtil.class);
        Object incre = redisUtil.incrementAndGet(RedisConstant.ORDER_SN_KEY, 1);
        return DateHelper.toYYYYMMDDHHMMSS(new Date())+ incre+"";
    }
}
