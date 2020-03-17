package com.sise.ccj.config;

import com.sise.ccj.exception.ServerException;
import com.sise.ccj.vo.HttpBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@ControllerAdvice
public class ServerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public HttpBody handleServerException(Exception e){
        if (e instanceof  ServerException) {
            ServerException exception = (ServerException) e;
            log.error("", e);
            return HttpBody.getInstance(exception.getCode(), exception.getMessage(), exception.getData());
        }else {
            log.error("", e);
            return HttpBody.getInstance(HttpBody.NOTE_CODE, HttpBody.ERROR);
        }
    }
}
