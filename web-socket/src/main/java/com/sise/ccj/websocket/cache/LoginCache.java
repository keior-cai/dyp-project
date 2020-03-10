package com.sise.ccj.websocket.cache;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginCache {
    private LoginCache(){}

    private static final Map<String, JSONObject> student = new ConcurrentHashMap<>();

    private static final Map<String, JSONObject> teacher = new ConcurrentHashMap<>();

    public static JSONObject getStudent(String token) {
        return student.get(token);
    }

    public static JSONObject getTeacher(String token) {
        return teacher.get(token);
    }
    public static void setStudent(String token, JSONObject student) {
        LoginCache.student.put(token, student);
    }

    public static void setTeacher(String token, JSONObject teacher) {
        LoginCache.teacher.put(token, teacher);
    }

    public static Map<String, JSONObject> getAllStudent(){
        return student;
    }
    public static Map<String, JSONObject> getAllTeacher(){
        return teacher;
    }
    static {
        JSONObject stu = new JSONObject();
        stu.put("studentId", "29ddd6ac-6bcd-4398-9bfc-534097852dc0");
        stu.put("name", "student1");
        student.put("29ddd6ac-6bcd-4398-9bfc-534097852dc0", stu);
        JSONObject teatch = new JSONObject();
        teatch.put("teacherId", "23132132");
        teatch.put("name", "教师1");
        teacher.put("23132132", teatch);
    }
}
