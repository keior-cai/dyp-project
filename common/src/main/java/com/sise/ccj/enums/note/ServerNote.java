package com.sise.ccj.enums.note;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServerNote {

    USER_NAME_PASSWD_ERROR(19100001, "用户名或密码错误!"),//提示信息应前端需求返回code=1

    LOGIN_TOO_MANY(19100002, "登陆失败过于频繁,请稍后再试!"),

    VERIFY_CODE_ERROR(19100003, "验证码错误!"),

    ACCOUNT_NOT_EXIST(19100004, "亲！你删除的帐号不存在"),

    ACCOUNT_NOT_PERMISSION(19100005, "亲，你的权限不足,请联系管理员"),

    EXIST_NOT_DEAL_WITH_TASK(19100006, "亲！你还存在未完成任务，请完成后在切换状态"),

    STATUS_NOT_CHANGE(19100007, "当前状态没发生变化"),

    STATUS_EXCEPTION(19100008,"错误状态"),

    TASK_TIME_OUT(19100009,"任务已超时"),

    TASK_NOT_EXIST(19100010,"任务不存在"),

    ACCOUNT_MAX_LOAD_ERROR(19100011,"最大负载不在范围,请输入0~99"),

    ACCOUNT_ROLE_ERROR(19100012,"账号角色有误"),

    PUBLIC_KEY_ERROR(19100013,"解密失败"),

    ACCOUNT_EXIST(19100014,"账号已存在"),

    EXPORT_DATA_IS_NULL(19100015,"导出数据为空"),

    DOWNLOAD_FILE_NOT_EXIST(19100016,"下载文件不存在"),

    DOWNLOAD_TASK_EXIST(19100017,"下载任务已存在"),

    BOT_NOT_EXIST_FAQ_ID(19100018,"bot 不存在该faq id"),

    HISTORY_NOT_EXIST(19100019,"记录不存在"),

    FAQ_INDEX_NOT_EXIST(19100020,"faq配置不存在"),

    HISTORY_NOT_THIS(19100021,"该记录不是你的"),
    
    AUDIO_NOT_EXISTS(19100022, "对话音频不存在"),
    
    DOWNLOAD_TOO_MUCH(19100023,"当前下载任务过多,赞不能下载"),

    EXPORT_TIME_NOT_NULL(19100024,"导出时间不能为空"),

    TIME_FORMAT_ERROR(19100024,"时间格式错误"),

    EXPORT_TIME_GT_ONE_MOUTH(19100025,"导出时间大于一个月"),

    ACCOUNT_ACCOUNT_LEN_GT_MAX_LEN(19100026,"姓名或者帐号长度不能大于50"),

    NOT_DELETE_LAST_ADMIN_ACCOUNT(19100027,"不能删除最后一个管理员帐号"),
    ;

    private int code;

    private String message;
}
