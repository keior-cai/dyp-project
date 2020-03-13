package com.sise.ccj.task.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Configuration("taskConfig")
@ConfigurationProperties(prefix = "dyp.task")
public class TaskConfig {
    private String orderTimeOutCron;

    private String movieDownCron;

    private String pSpaceDownCron;

    private String orderStaticsCron;

    private String sendJobCron;

    private String pullJobCron;

    private String env;

    private Integer orderTomeOut;

    private String pullTaskCron;

    @PostConstruct
    public void init(){
        log.info("{}", this);
    }
}
