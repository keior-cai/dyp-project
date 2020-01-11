package com.sise.ccj.enums.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 此类的描述是：Http请求的状态码
 *
 * 错误码规则如下：
 * 负数标识错误
 * 1~2位表示YicallError错误码前缀：19
 * 3~4位表示java服务：10
 * 5-8为表示异常
 *
 * 暂时只支持%s来补充信息，以后需要更改，可以在YicallException中修改对应的构造方法即可
 */
@Getter
@AllArgsConstructor
public enum ServerError {

    UNKNOWN_ERROR(19100000, "系统异常，请联系系统管理员"),
    
    LOGIN_MISS_SEQNO(19000019, "参数异常,缺失SeqNo"),
    
    INVALID_LOGIN(19100031, "登录非法token"),
    
    OPEN_PARAM_ERROR(19000017, "%s 参数错误: %s"),

    PERMISSION(19000016,"权限不足,请联系管理员"),
    ;

    private int code;

    private String message;
}
