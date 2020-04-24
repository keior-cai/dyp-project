package com.sise.common.utils;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class RequestBodyUtils {
    private RequestBodyUtils() {
    }

    private MultiValueMap<String, String> of(String key, String value) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("key", value);
        return map;
    }

    private MultiValueMap<Object, Object> of(Object... object) throws IllegalAccessException {
        if (object.length == 0) {
            return new LinkedMultiValueMap<>();
        }
        if (object.length % 2 > 0) {
            throw new IllegalAccessException("object 长度有误");
        }
        MultiValueMap<Object, Object> map = new LinkedMultiValueMap<>();
        for (int i = 0; i < object.length; i += 2) {
            map.add(object[i], object[i+1]);
        }
        return map;
    }
}
