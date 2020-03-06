package com.sise.ccj.task.out;

import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.constant.RedisConstant;
import com.sise.ccj.mapper.OrderStaticsMapper;
import com.sise.ccj.pojo.common.OrderStaticsPO;
import com.sise.ccj.service.OrderService;
import com.sise.ccj.task.job.Job2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderStaticsTime
 * @Description
 * @Author CCJ
 * @Date 2020/2/7 0:21
 **/
@Component
public class OrderStaticsTime implements Job2 {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private OrderStaticsMapper orderStaticsMapper;

    @Override
    public void execute(String db) {
        Map<Object, Object> countMap = redisUtil.entries(RedisConstant.ORDER_COUNT);
        Map<Object, Object> totalMap = redisUtil.entries(RedisConstant.ORDER_TOTAL);
        if (CollectionUtils.isEmpty(countMap)){
            return;
        }
        OrderStaticsPO staticsPO = null;
        for (Map.Entry<Object, Object> entry : countMap.entrySet()){
            staticsPO = new OrderStaticsPO();
            staticsPO.setDbPrefix(CommonConstant.TABLE_SPACE.replace(CommonConstant.TABLE_SPACE_ID, entry.getKey()+""));
            staticsPO.setCount(Integer.parseInt(entry.getValue()+""));
            staticsPO.setTotal(Double.parseDouble(totalMap.get(entry.getKey())+""));
            staticsPO.setYId(Integer.parseInt(entry.getKey()+""));
            staticsPO.setCreateTime(new Date());
            orderStaticsMapper.inserUpdate(staticsPO);
            redisUtil.hremove(RedisConstant.ORDER_COUNT, entry.getKey());
            redisUtil.hremove(RedisConstant.ORDER_TOTAL, entry.getKey());
        }
    }
}
