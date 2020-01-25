package com.sise.ccj.request.admin;

import com.sise.ccj.request.BaseRequest;
import lombok.Data;

@Data
public class SpaceRequest extends BaseRequest {
    private String status;

    private String startTime;

    private String endTime;

    private String name;

    private String dbPrefix;
}
