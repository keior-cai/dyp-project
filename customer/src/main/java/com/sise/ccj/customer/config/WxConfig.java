package com.sise.ccj.customer.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ClassName WxConfig
 * @Description
 * @Author CCJ
 * @Date 2020/1/30 14:42
 **/
@Component
public class WxConfig {
    @Autowired
    private CustomerConfig customerConfig;

    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService=new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpConfigStorage=new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(customerConfig.getMpAppId());
        wxMpConfigStorage.setSecret(customerConfig.getMpAppSecret());
        return wxMpConfigStorage;
    }
}
