package com.sise.ccj.task.out;

import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.RedisConstant;
import com.sise.ccj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName OrderStaticsTime
 * @Description
 * @Author CCJ
 * @Date 2020/2/7 0:21
 **/
@Component
public class OrderStaticsTime {

    @Autowired
    private RedisUtil redisUtil;

    @Scheduled(cron = "0 50 23 * * ?")
    public void exectu(){
        Map<Object, Object> countMap = redisUtil.entries(RedisConstant.ORDER_COUNT);
        Map<Object, Object> totalMap = redisUtil.entries(RedisConstant.ORDER_TOTAL);
        // 持久化
    }

}
