package com.sise.ccj.vo;

import lombok.Data;

@Data
public class AccountInfoVO {
    private String username;

    private String name;
    /**
     * 头像
     */
    private String nickName;

    private Boolean isAdmin;

    private String publicKey;

    private Integer workStatus;

    private Boolean autoPlay;

    private Double playSpeed;

    private Double playVolume;
}
