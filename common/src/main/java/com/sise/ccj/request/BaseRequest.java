package com.sise.ccj.request;

import com.sise.ccj.exception.ServerException;
import com.sise.ccj.utils.Pair;
import lombok.Data;

import java.util.regex.Pattern;

@Data
public abstract class BaseRequest {
    private Integer page;

    private Integer size;

    private String dbPrefix;

    private static final String PATTERN = ".*?${param}.*";

    public void check(){
        if (this.page <= 0 && size <= 0) {
            throw new ServerException("page and size is not lt zero");
        }
    }

    public static Pattern getLikeField(String str){
        return Pattern.compile(PATTERN.replace("${param}", str));
    }

    public  Pair<Long, Integer> getPageable(){
        return new Pair<>((long)(page-1) * size, size);
    }
}
