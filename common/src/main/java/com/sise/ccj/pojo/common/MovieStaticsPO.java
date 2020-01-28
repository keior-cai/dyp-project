package com.sise.ccj.pojo.common;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName MovieStaticsPO
 * @Description
 * @Author CCJ
 * @Date 2020/1/29 2:39
 **/
@Data
public class MovieStaticsPO {
    private Integer id;

    private String name;

    private String imgUrl;

    private Double total;

    private Integer count;

    private Integer turnCount;

    private String title;

    private Double pointCount;

    private Date createTime;

    private Date updateTime;

    //=====================
    private String dbPrefix;
}
