package com.sise.ccj.request;

import lombok.Data;

/**
 * @ClassName OrderRequest
 * @Description
 * @Author CCJ
 * @Date 2020/2/5 15:32
 **/
@Data
public class OrderRequest extends BaseRequest {

    private String orderSn;

    private Integer status;

    private String openId;

    private String startTime;

    private String endTime;
}
