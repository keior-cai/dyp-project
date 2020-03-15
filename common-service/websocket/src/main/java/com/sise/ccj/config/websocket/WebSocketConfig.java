package com.sise.ccj.config.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import java.util.Map;

/**
 * @ClassName WebSocketConfig
 * @Description
 * @Author CCJ
 * @Date 2019/9/13 14:53
 **/
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    @Autowired
    private Map<String, WebSocketHandler> webSocketHandlers;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        if (CollectionUtils.isEmpty(webSocketHandlers)){
            return;
        }
        for (Map.Entry<String, WebSocketHandler> handler : webSocketHandlers.entrySet()) {
            registry.addHandler(handler.getValue(), handler.getKey()).setAllowedOrigins("*");
        }
    }
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        // ws 传输数据的时候，数据过大有时候会接收不到，所以在此处设置bufferSize
        container.setMaxTextMessageBufferSize(5120000);
        container.setMaxBinaryMessageBufferSize(5120000);
        container.setMaxSessionIdleTimeout(15 * 600000L);
        return container;
    }
}