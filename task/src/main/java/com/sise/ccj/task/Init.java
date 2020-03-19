package com.sise.ccj.task;

import com.sise.ccj.task.init.InitTask;
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
