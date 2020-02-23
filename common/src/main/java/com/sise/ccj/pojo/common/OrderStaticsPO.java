package com.sise.ccj.pojo.common;

import lombok.Data;

import java.util.Date;

@Data
public class OrderStaticsPO {
    private Integer id;
    private Integer count;
    private Double total;
    private Integer yId;
    private Date createTime;
    private Date updateTime;

    //===========================
    private String dbPrefix;
}
