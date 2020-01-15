package com.sise.ccj.admin;

import com.sise.ccj.admin.config.AdminConfig;
import com.sise.ccj.config.mysql.MyBatisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.sise.ccj")
@EnableConfigurationProperties(value = {AdminConfig.class, MyBatisConfig.class})
public class AdminApplication {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

//    @PostConstruct
//    private void test(){
//        MongoDatabase database =  mongoTemplate.getDb();
//        log.info("{}", database);
//    }
}
