package com.sise.ccj.pojo.admin;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
public class DypYYCollection {
    private String name;

    private String address;

    private ObjectId adminId;

    private List<Object> labels;

    private String deleted;

    private Date createTime;

    private Date updateTime;
}
