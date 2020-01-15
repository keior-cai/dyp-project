package com.sise.ccj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpBody<T> {

    public static final HttpBody<Object> SUCCESS = new HttpBody<>(0, "OK", Collections.emptyMap());

    public static final int NOTE_CODE = 1;

    /**
     * code 为0 时为成功
     * code 为1 时为有提示语 常量参见 CommonConstant.NOTE_CODE
     * code 为大等于19100000 为错误
     */

    public static final String ERROR = "系统异常，请联系管理员";

    private int code;

    private String message;

    private T data;


    private HttpBody(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public static <T> HttpBody<T> getSucInstance(T data) {
        return new HttpBody<>(SUCCESS.code, SUCCESS.message, data);
    }

    public static <T> HttpBody<T> getInstance(int code, String message) {
        return new HttpBody<>(code, message);
    }

    public static <T> HttpBody<T> getInstance(int code, String message, T data) {
        return new HttpBody<>(code, message, data);
    }
}
