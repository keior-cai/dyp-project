package com.sise.ccj.websocket.message.handle;

import com.sise.ccj.websocket.cache.HandleCache;
import com.sise.ccj.websocket.constants.WebSocketConstant;
import com.sise.ccj.websocket.utils.Pair;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Slf4j
@Component("teacherMsgHandle")
public class TeacherMsgHandle implements MessageHandle {

    @Override
    public void handle(WebSocketSession session, JSONObject msg) {
        if (msg.getString(WebSocketConstant.COMMAND).equals(WebSocketConstant.CALL)){
            String teacherId = msg.getString("teacherId");
            String studentId = msg.getString("studentId");
            if (!HandleCache.containsStudent(studentId)){
                JSONObject json = new JSONObject();
                json.put("message", "学生不在线");
                TextMessage message = new TextMessage(json.toJSONString());
                try {
                    session.sendMessage(message);
                } catch (IOException e) {
                    log.error("", e);
                }
            } else {
                if (HandleCache.isCall(studentId)){
                    JSONObject json = new JSONObject();
                    json.put("message", "学生正在通话");
                    TextMessage message = new TextMessage(json.toJSONString());
                    try {
                        session.sendMessage(message);
                    } catch (IOException e) {
                        log.error("", e);
                    }
                }else{
                    Pair<JSONObject, WebSocketSession> pair =  HandleCache.getTeacher(teacherId);
                    pair.getFirst().put("studentId", studentId);
                    HandleCache.connector(studentId, teacherId);
                    JSONObject json = new JSONObject();
                    json.put("message", "连接成功");
                    TextMessage message = new TextMessage(json.toJSONString());
                    try {
                        session.sendMessage(message);
                        HandleCache.getStudent(studentId).getSecond().sendMessage(message);
                    } catch (IOException e) {
                        log.error("", e);
                    }
                }
            }
        }
    }
}
