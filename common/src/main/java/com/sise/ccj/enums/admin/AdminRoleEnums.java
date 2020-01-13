package com.sise.ccj.enums.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminRoleEnums {

    SUPER_ADMIN(true, "超级管理员"),
    GENERAL_ADMIN(false, "普通管理员"),
    ;

    private boolean role;

    private String name;

}
