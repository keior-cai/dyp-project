package com.sise.ccj.pojo.common;

import lombok.Data;

import java.util.Date;

@Data
public class StaticsCustomerCountPO {
    private Integer id;

    private Integer userId;

    private Integer count;

    private Integer year;

    private Integer mouth;

    private Integer day;

    private Date createTime;

    private Date updateTime;
}
