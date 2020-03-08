package com.sise.ccj.websocket.message.handle;

import com.sise.ccj.websocket.cache.HandleCache;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.socket.WebSocketSession;

//@Service
public class StudentNotifyServer implements MessageHandle {

    @Override
    public void handle(WebSocketSession session, JSONObject msg) {
        String studentId = msg.getString("id");
        HandleCache.addStudent(studentId, msg, session);
    }
}
