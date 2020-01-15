package com.sise.ccj.config;

import com.sise.ccj.exception.ServerException;
import com.sise.ccj.vo.HttpBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class ServerExceptionHandler {

    @ExceptionHandler(ServerException.class)
    public HttpBody handleServerException(Exception e){
            ServerException exception = (ServerException) e;
            return HttpBody.getInstance(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public HttpBody handleException(){
        return HttpBody.getInstance(HttpBody.NOTE_CODE, HttpBody.ERROR);
    }
}
