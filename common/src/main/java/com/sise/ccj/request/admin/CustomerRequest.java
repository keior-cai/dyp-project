package com.sise.ccj.request.admin;

import com.sise.ccj.request.BaseRequest;
import lombok.Data;

@Data
public class CustomerRequest extends BaseRequest {
    private String startTime;

    private String endTime;

    private Integer isVip;

    private String name;
}
