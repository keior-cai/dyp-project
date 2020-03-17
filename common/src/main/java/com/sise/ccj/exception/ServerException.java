package com.sise.ccj.exception;

import com.sise.ccj.enums.error.ServerError;
import com.sise.ccj.enums.note.ServerNote;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;

/**
 * @author jjluo
 * @date 2018/6/25
 */
@Getter
@ToString
public class ServerException extends RuntimeException {
	
	private static final long serialVersionUID = -8150974659067902169L;

	private static final int ERROR = -1;

	private static final String ERROR_MESSAGE = "系统异常，请联系管理员";

	private static final ServerException errorInstant = new ServerException(ERROR, ERROR_MESSAGE);

	private int code;

    private String message;

    private String data;
    
    public ServerException(int code, String message) {
    	this.code = code;
    	this.message = message;
    }
    public ServerException(int code, String message, String data) {
    	this.code = code;
    	this.message = message;
    	this.data = data;
    }

    public ServerException(String message) {
        this.code = ERROR;
        this.message = message;
    }

    public ServerException(ServerError error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public ServerException(ServerError error, Object... params) {
        this.code = error.getCode();
        this.message = String.format(error.getMessage(), params);
    }
    
    public ServerException(ServerNote note) {
    	this.code = note.getCode();
        this.message = note.getMessage();
    }


}
