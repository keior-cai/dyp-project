package com.sise.ccj.pojo.admin;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserPO {
    private Integer id;

    private String name;

    private String userName;

    private String password;

    private String phone;

    private String avatar;

    private Integer role;

    private String status;

    private String ip;

    private Integer deleted;

    private Date createTime;

    private Date updateTime;

    private String info;

    private String openUrl;


    // ========================
    private String token;

    private String tableSpace;

    public UserPO(String tableSpace){
        this.tableSpace = tableSpace;
    }
}
