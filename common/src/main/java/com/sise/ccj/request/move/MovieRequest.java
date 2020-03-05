package com.sise.ccj.request.move;

import com.sise.ccj.request.BaseRequest;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName MoveRequest
 * @Description
 * @Author CCJ
 * @Date 2019/12/29 21:12
 **/

@Data
public class MovieRequest extends BaseRequest {

    private String id;

    private String title;

    private String context;

    private String name;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String startTime;

    private String endTime;

    private Integer dyy;

    public void addCreateTimeAndUpdateTime() {
        Date date = new Date();
        setUpdateTime(date);
        setCreateTime(date);
    }
}
