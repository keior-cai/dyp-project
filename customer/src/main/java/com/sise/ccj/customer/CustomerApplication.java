package com.sise.ccj.customer;

import com.sise.ccj.config.mysql.MyBatisConfig;
import com.sise.ccj.customer.config.CustomerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.sise.ccj")
@EnableConfigurationProperties(value = {CustomerConfig.class, MyBatisConfig.class})
public class CustomerApplication {


    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}
