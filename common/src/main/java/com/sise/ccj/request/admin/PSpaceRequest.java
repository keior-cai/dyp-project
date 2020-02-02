package com.sise.ccj.request.admin;

import com.sise.ccj.request.BaseRequest;
import lombok.Data;

/**
 * @ClassName PSpaceRequest
 * @Description
 * @Author CCJ
 * @Date 2020/2/2 16:12
 **/
@Data
public class PSpaceRequest extends BaseRequest {

    private Integer id;

    private Integer mId;

    private Integer sId;

    private String date;

    private Integer status;

    private String startTime;

    private String endTime;

    //==========================
    private String dbPrefix;
}
