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
@Component("studentMsgHandle")
public class StudentMsgHandle implements MessageHandle {

    @Override
    public void handle(WebSocketSession session, JSONObject msg) {
        log.info("{}", msg);
        if (msg.getString("type").equals(WebSocketConstant.CALL)){
            String teacherId = msg.getString("teacherId");
            String studentId = msg.getString("studentId");
            JSONObject teacherInfo = HandleCache.getTeacher(teacherId).getFirst();
            if (teacherInfo == null){
                JSONObject json = new JSONObject();
                json.put("message", "老师不在线");
                TextMessage message = new TextMessage(json.toJSONString());
                try {
                    session.sendMessage(message);
                } catch (IOException e) {
                    log.error("", e);
                }
            } else {
                if (HandleCache.isCall(teacherId)){
                    JSONObject json = new JSONObject();
                    json.put("message", "老师正在通话");
                    TextMessage message = new TextMessage(json.toJSONString());
                    try {
                        session.sendMessage(message);
                    } catch (IOException e) {
                        log.error("", e);
                    }
                }else{
                    Pair<JSONObject, WebSocketSession> pair =  HandleCache.getStudent(studentId);
                    pair.getFirst().put("teacherId", teacherId);
                    HandleCache.connector(studentId, teacherId);
                    JSONObject json = new JSONObject();
                    json.put("message", "连线成功");
                    TextMessage message = new TextMessage(json.toJSONString());
                    try {
                        session.sendMessage(message);
                        HandleCache.getTeacher(teacherId).getSecond().sendMessage(message);
                    } catch (IOException e) {
                        log.error("", e);
                    }
                }
            }
        }
    }
}
