package com.sise.ccj.request.move;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName MoveRequest
 * @Description
 * @Author CCJ
 * @Date 2019/12/29 21:12
 **/

@Data
public class MoveRequest {

    private String id;

    private String title;

    private String context;

    private String name;

    private Date createTime;

    private Date updateTime;

    private Integer dyy;

    public void addCreateTimeAndUpdateTime(){
        Date date = new Date();
        setUpdateTime(date);
        setCreateTime(date);
    }
}
