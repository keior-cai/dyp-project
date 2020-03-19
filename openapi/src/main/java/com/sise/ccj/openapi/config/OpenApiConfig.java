package com.sise.ccj.openapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "openapi")
public class OpenApiConfig {
    private String rsaPublic;
    private String rsaPrivate;
}
