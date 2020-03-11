package com.sise.ccj.task;

import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.TimeConstant;
import com.sise.ccj.task.cluster.master.MasterCache;
import com.sise.ccj.task.config.TaskConfig;
import com.sise.ccj.task.constant.TaskConstant;
import com.sise.ccj.task.down.MovieDownJob;
import com.sise.ccj.task.out.OrderTimeOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Init {

    @Autowired
    private TaskConfig taskConfig;

    @Autowired
    private RedisUtil redisUtil;


    @PostConstruct
    public void init(){
        selectorMaster();
        TaskQuartzManager.addJob("TimeOutJob","TimeOutJobJobTrigger", OrderTimeOut.class, taskConfig.getOrderTimeOutCron());
        TaskQuartzManager.addJob("MovieDownJob","MovieDownJobTrigger", MovieDownJob.class, taskConfig.getOrderTimeOutCron());
        TaskQuartzManager.addJob("PSpaceDownJob","PSpaceDownJobTrigger", MovieDownJob.class, taskConfig.getOrderTimeOutCron());
    }

    public void selectorMaster(){
        MasterCache.isMaster =  redisUtil.setIfAbsent(TaskConstant.MASTER_ENV, taskConfig.getEnv(), TimeConstant.TOW_MINUTE_SECOND);
        if (!MasterCache.isMaster){
            MasterCache.masterEnv = redisUtil.get(TaskConstant.MASTER_ENV)+"";
        }else {
            MasterCache.masterEnv = taskConfig.getEnv();
        }
    }
}
