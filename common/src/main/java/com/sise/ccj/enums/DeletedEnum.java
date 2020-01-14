package com.sise.ccj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  DeletedEnum {
    DELETED(true, "已删除"),
    NOT_DELETED(false, "未删除"),
    ;

    private boolean isDelete;

    private String desc;
}
