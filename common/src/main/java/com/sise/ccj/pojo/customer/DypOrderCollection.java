package com.sise.ccj.pojo.customer;

import lombok.Data;

import java.util.Date;

@Data
public class DypOrderCollection {
    private String orderSn;

    private String name;

    private String phone;

    private Double total;

    private Boolean isVip;

    private String userName;

    private String openId;

    private String num;

    private Date createTime;

    private Date updateTime;

    private Date playTime;

    private Date timeOut;

    private Integer orderStatus;

    private Double actualTotal;

    private Double discount;
}
