package com.sise.ccj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  DeletedEnum {
    DELETED(1, "已删除"),
    NOT_DELETED(0, "未删除"),
    ;

    private int isDelete;

    private String desc;
}
