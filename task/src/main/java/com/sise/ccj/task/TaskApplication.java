package com.sise.ccj.task;

import com.sise.ccj.config.mysql.MyBatisConfig;
import com.sise.ccj.task.config.TaskConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.sise.ccj")
@EnableConfigurationProperties(value = {MyBatisConfig.class, TaskConfig.class})
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }

}
