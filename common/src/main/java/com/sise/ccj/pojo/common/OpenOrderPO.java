package com.sise.ccj.pojo.common;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName OpenOrderPO
 * @Description
 * @Author CCJ
 * @Date 2020/2/6 16:29
 **/
@Data
public class OpenOrderPO {
    private Integer id;

    private String info;

    private Integer type;

    private String model;

    private String url;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    //=================
    private String dbPrefix;
}
