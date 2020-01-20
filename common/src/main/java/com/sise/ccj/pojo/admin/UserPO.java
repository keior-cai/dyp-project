package com.sise.ccj.pojo.admin;

import lombok.Data;

import java.util.Date;

@Data
public class UserPO {
    private Integer id;

    private String name;

    private String userName;

    private String password;

    private String phone;

    private String avatar;

    private Integer role;

    private Integer status;

    private String ip;

    private Integer deleted;

    private Date createTime;

    private Date updateTime;


    // ========================
    private String token;

    private String tableSpace;
}
