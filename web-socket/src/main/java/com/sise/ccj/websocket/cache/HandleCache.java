package com.sise.ccj.websocket.cache;


import com.sise.ccj.websocket.utils.Pair;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HandleCache {

    private static final Map<String, Pair<JSONObject, WebSocketSession>> student = new ConcurrentHashMap<>();


    private static final Map<String, Pair<JSONObject, WebSocketSession>> teacher = new ConcurrentHashMap<>();

    private static final Map<String, String> connector = new ConcurrentHashMap<>();

    private HandleCache(){}



    public  static void addStudent(String token, JSONObject student, WebSocketSession session){
        if (student == null){
            return;
        }
        HandleCache.student.putIfAbsent(token, new Pair<>(student, session));
    }

    public static Pair<JSONObject, WebSocketSession> getStudent(String token){
        return student.get(token);
    }

    public  static void addTeacher(String token, JSONObject teacher, WebSocketSession session){
        if (teacher == null){
            return;
        }
        HandleCache.teacher.putIfAbsent(token, new Pair<>(teacher, session));
    }

    public static Pair<JSONObject, WebSocketSession> getTeacher(String token){
        return teacher.get(token);
    }

    public static boolean containsStudent(String token){
        return student.containsKey(token);
    }

    public static boolean containsTeacher(String token){
        if (teacher.isEmpty()){
            return false;
        }
        return teacher.containsKey(token);
    }

    public static void delStudent(String token){
        student.remove(token);
        connector.remove(token);
    }

    public static void delTeacher(String token){
        teacher.remove(token);
        connector.remove(token);
    }

    public static void connector(String studentId, String teacherId){
        String stuPrefix = "student_";
        String teachPrefix = "teacher_";
        connector.put(stuPrefix+studentId, teachPrefix+teacherId);
        connector.put(teachPrefix+teacherId, stuPrefix+studentId);
    }

    public static boolean isCall(String token){
        return connector.containsKey(token);
    }

    public static String getCallToken(String token){
        return connector.get(token);
    }
}
