package com.sise.ccj.enums.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminRoleEnums {

    SUPER_ADMIN(1, "超级管理员"),
    GENERAL_ADMIN(0, "普通管理员"),
    ;

    private int role;

    private String name;

}
