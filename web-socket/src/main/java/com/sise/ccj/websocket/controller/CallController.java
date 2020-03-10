package com.sise.ccj.websocket.controller;

import com.sise.ccj.websocket.cache.LoginCache;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@CrossOrigin("*")
@RestController
public class CallController {


    @GetMapping("/teacherLogin")
    public JSONObject teacherLogin(HttpServletResponse rs){
        JSONObject response = new JSONObject();
        String token = UUID.randomUUID().toString();
        JSONObject teacher = new JSONObject();
        teacher.put("name", "老师1");
        teacher.put("teacherId", token);
        teacher.put("type", "teacher");
        response.put("token", token);
        response.put("data", teacher);
        LoginCache.setTeacher(token, teacher);
        rs.addCookie(new Cookie("token", token));
        return response;
    }

    @GetMapping("/studentLogin")
    public JSONObject studentLogin(HttpServletResponse rs){
        JSONObject response = new JSONObject();
        String token = UUID.randomUUID().toString();
        JSONObject student = new JSONObject();
        student.put("name", "学生1");
        student.put("studentId", token);
        student.put("type", "student");
        response.put("token", token);
        response.put("data", student);
        LoginCache.setStudent(token, student);
        rs.addCookie(new Cookie("token", token));
        return response;
    }

    @GetMapping("/onLineTeacher")
    public Map<String, JSONObject> onLineTeacher(){
        return LoginCache.getAllTeacher();
    }

    @GetMapping("/onLineStudent")
    public Map<String, JSONObject> onLineStudent(){
        return LoginCache.getAllStudent();
    }

}
