package com.sise.ccj.pojo.common;

import lombok.Data;

import java.util.Date;

@Data
public class MovieStaticsPO {
    private Integer id;

    private Integer movieId;

    private Integer totalOrder;

    private Double totalMoney;

    private Integer vipCount;

    private Integer count;

    private Double vipMoney;

    private Date createTime;

    private Date updateTime;
}
