package com.sise.ccj.task.config;

import lombok.Data;

@Data
public class TaskConfig {
    private String orderTimeOutCron;

    private String movieDownCron;

    private String pSpaceDownCron;

    private String orderStaticsCron;

    private String sendJobCron;

    private String env;

    private Integer orderTomeOut;

    private String pullTaskCron;
}
