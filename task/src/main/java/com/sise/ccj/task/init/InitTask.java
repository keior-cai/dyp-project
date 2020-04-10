package com.sise.ccj.task.init;

import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.TimeConstant;
import com.sise.ccj.task.TaskQuartzManager;
import com.sise.ccj.task.cluster.master.MasterCache;
import com.sise.ccj.task.cluster.slave.*;
import com.sise.ccj.task.config.TaskConfig;
import com.sise.ccj.task.constant.TaskConstant;
import com.sise.ccj.task.down.MovieDownJob;
import com.sise.ccj.task.down.PSpaceDown;
import com.sise.ccj.task.down.VipDown;
import com.sise.ccj.task.open.SendJob;
import com.sise.ccj.task.out.OrderStaticsTime;
import com.sise.ccj.task.out.OrderTimeOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitTask {
    @Autowired
    private TaskConfig taskConfig;

    @Autowired
    private RedisUtil redisUtil;

    private void selectorMaster() {
        MasterCache.isMaster = redisUtil.setIfAbsent(TaskConstant.MASTER_ENV, taskConfig.getEnv(), TimeConstant.TOW_MINUTE_SECOND);
        if (MasterCache.isMaster) {
            MasterCache.masterEnv = redisUtil.get(TaskConstant.MASTER_ENV) + "";
            // 维持master状态
            new Thread(() -> {
                while (true) {
                    redisUtil.set(TaskConstant.MASTER_ENV, taskConfig.getEnv(), TimeConstant.TOW_MINUTE_SECOND);
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        log.info("", e);
                    }
                }
            });
        } else {
            MasterCache.masterEnv = taskConfig.getEnv();
        }
        log.info("此节点为: " + (MasterCache.isMaster ? "master节点" : "slave节点") + " masterEnv=" + MasterCache.masterEnv);
    }

    public void run() {
        selectorMaster();
        if (MasterCache.isMaster && MasterCache.isFirst) {
            log.info("开始加入任务");
            TaskQuartzManager.addJob("TimeOutJob", "TimeOutJobJobTrigger", OrderTimeOut.class, taskConfig.getOrderTimeOutCron());
            TaskQuartzManager.addJob("MovieDownJob", "MovieDownJobTrigger", MovieDownJob.class, taskConfig.getMovieDownCron());
            TaskQuartzManager.addJob("PSpaceDownJob", "PSpaceDownJobTrigger", PSpaceDown.class, taskConfig.getPSpaceDownCron());
            TaskQuartzManager.addJob("VipDownJob", "VipDownJobTrigger", VipDown.class, taskConfig.getVipDownCron());
            TaskQuartzManager.addJob("OrderStaticsJob", "OrderStaticsJobTrigger", OrderStaticsTime.class, taskConfig.getOrderStaticsCron());
            TaskQuartzManager.addJob("SendJob", "SendJobTrigger", SendJob.class, taskConfig.getSendJobCron());
        }
        if (MasterCache.isFirst){
            TaskQuartzManager.addJob("PullOrderTimeOutJob", "PullOrderTimeOutJobTrigger", PullOrderTimeOutJob.class, taskConfig.getPullTaskCron());
            TaskQuartzManager.addJob("PullSendJob", "PullSendJobTrigger", PullSendJob.class, taskConfig.getPullTaskCron());
            TaskQuartzManager.addJob("PullPSpaceDownJob", "PullPSpaceDownJobTrigger", PullPSpaceDownJob.class, taskConfig.getPullTaskCron());
            TaskQuartzManager.addJob("PullVipDownJob", "PullVipDownJobTrigger", PullVipDownJob.class, taskConfig.getPullTaskCron());
            TaskQuartzManager.addJob("PullMovieDownJob", "PullMovieDownJobTrigger", PullMovieDownJob.class, taskConfig.getPullTaskCron());
            MasterCache.isFirst = false;
            redisUtil.hmSet(PullHelp.key,taskConfig.getEnv(), "1");
        }
    }
}
