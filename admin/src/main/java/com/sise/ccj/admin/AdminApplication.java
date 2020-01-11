package com.sise.ccj.admin;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.sise.ccj"})
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
