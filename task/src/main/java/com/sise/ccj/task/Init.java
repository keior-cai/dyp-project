package com.sise.ccj.task;

import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.TimeConstant;
import com.sise.ccj.task.cluster.master.MasterCache;
import com.sise.ccj.task.cluster.slave.PullOrderTimeOutJob;
import com.sise.ccj.task.config.TaskConfig;
import com.sise.ccj.task.constant.TaskConstant;
import com.sise.ccj.task.down.MovieDownJob;
import com.sise.ccj.task.down.PSpaceDown;
import com.sise.ccj.task.init.InitTask;
import com.sise.ccj.task.open.SendJob;
import com.sise.ccj.task.out.OrderStaticsTime;
import com.sise.ccj.task.out.OrderTimeOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Init implements ApplicationRunner {

   @Autowired
   private InitTask initTask;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initTask.run();
        TaskQuartzManager.startJobs();
    }
}
