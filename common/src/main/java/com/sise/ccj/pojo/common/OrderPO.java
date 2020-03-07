package com.sise.ccj.pojo.common;

import lombok.Data;

import java.util.Date;

@Data
public class OrderPO {
    private Integer id;

    private String orderSn;

    private String openId;

    private Integer num;

    private Double total;

    private Double price;

    private Integer isVip;

    private Integer status;

    private String info;

    private Integer yId;

    private Integer mId;

    private Integer psId;

    private Date createTime;

    private Date updateTime;

    private String movieName;

    private String movieUrl;

    private String title;

    private String content;

    //======================
    private String dbPrefix;

    private String location;

    private long outTime;
}
