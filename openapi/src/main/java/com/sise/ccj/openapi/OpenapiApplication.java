package com.sise.ccj.openapi;

import com.sise.ccj.config.mysql.MyBatisConfig;
import com.sise.ccj.openapi.config.OpenApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {MyBatisConfig.class, OpenApiConfig.class})
public class OpenapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenapiApplication.class, args);
    }

}
