package com.sise.ccj.request.admin;

import com.sise.ccj.request.BaseRequest;
import lombok.Data;

@Data
public class AdminRequest extends BaseRequest {
    private String userName;

    private String startTime;

    private String endTime;

    private String name;
}
