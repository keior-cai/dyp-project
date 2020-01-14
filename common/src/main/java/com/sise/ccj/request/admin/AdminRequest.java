package com.sise.ccj.request.admin;

import com.sise.ccj.request.BaseRequest;
import lombok.Data;

@Data
public class AdminRequest extends BaseRequest {

    private String id;

    private String userName;

    private String password;

    private String name;

    private String startTime;

    private String endTime;

}
