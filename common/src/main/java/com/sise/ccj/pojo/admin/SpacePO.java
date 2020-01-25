package com.sise.ccj.pojo.admin;

import lombok.Data;

import java.util.Date;

@Data
public class SpacePO {
    private Integer id;

    private String name;

    private String address;

    private Integer total;

    private Integer num;

    private String status;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    //===================

    private String dbPrefix;
}
