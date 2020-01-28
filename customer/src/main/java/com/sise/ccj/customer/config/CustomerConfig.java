package com.sise.ccj.customer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName CustomerConfig
 * @Description
 * @Author CCJ
 * @Date 2020/1/28 18:19
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "customer")
public class CustomerConfig {
    private String loginPath;
    private String indexPath;
}
