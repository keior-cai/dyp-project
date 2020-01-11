package com.sise.ccj.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.sise.ccj.utils.DateHelper;
import org.bson.types.ObjectId;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ControllerAdviceConfig
 * @Description
 * @Author CCJ
 * @Date 2019/12/29 21:05
 **/
@ControllerAdvice
public class ControllerAdviceConfig implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        SerializeConfig config = new SerializeConfig();
        config.put(ObjectId.class, (serializer, object, fieldName, fieldType, features) -> {
            SerializeWriter out = serializer.getWriter();
            if (object == null) {
                serializer.getWriter().writeNull();
                return;
            }
            out.write("\"" + ((ObjectId) object).toString() + "\"");
        });
        config.put(Date.class, (serializer, object, fieldName, fieldType, features) -> {
            SerializeWriter out = serializer.getWriter();
            if (object == null) {
                serializer.getWriter().writeNull();
                return;
            }
            out.write("\"" + new SimpleDateFormat(DateHelper.YYYY_MM_DD_HH_MM_SS).format(object) + "\"");
        });
        return JSONObject.parse(JSON.toJSONString(o, config));
    }
}
