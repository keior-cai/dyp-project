package com.sise.ccj.pojo.common;

import lombok.Data;

import java.util.Date;

@Data
public class MoviePO {
    private Integer id;

    private String name;

    private String content;

    private String labels;

    private Double price;

    private Double vipPrice;

    private Integer status;

    private Date upTime;

    private String director;

    private String actor;

    private Integer deleted;

    private Date createTime;

    private Date updateTime;

    // ==========================
    private String dbPrefix;
}
