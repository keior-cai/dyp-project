package com.sise.ccj.pojo.admin;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerPO {
    private Integer id;

    private String name;

    private String openId;

    private String wechatName;

    private String img;

    private Integer sex;

    private Integer status;

    private Date createTime;

    private Integer isVip;

    private Integer vipMouth;

    private Date vipCreateTime;

    private Date updateTime;

    private String ip;

    private String payPassword;



    //======================

    private String token;

    private String dbPrefix;

    private String tableSpace;

    private Integer yId;
}
