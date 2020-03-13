package com.sise.ccj.pojo.common;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName PSpacePO
 * @Description
 * @Author CCJ
 * @Date 2020/2/2 15:33
 **/
@Data
public class PSpacePO {
    private Integer id;

    private Integer mId;

    private Integer sId;

    private String upTime;

    private String downTime;

    private Integer status;

    private String date;

    private Date createTime;

    private Date updateTime;

    private Date stroeTime;

    private String info;

    private Integer num;

    private Integer deleted;


    //=================
    private String dbPrefix;

    private String name;

    private String movieName;

    private String title;

    private String content;

    private String address;

    private Integer total;

    private Double price;

    private Double vipPrice;

    private Integer movieStatus;
}
