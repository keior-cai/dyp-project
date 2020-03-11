package com.sise.ccj.task.config;

import lombok.Data;

@Data
public class TaskConfig {
    private String orderTimeOutCron;

    private String env;

    private Integer orderTomeOut;
}
