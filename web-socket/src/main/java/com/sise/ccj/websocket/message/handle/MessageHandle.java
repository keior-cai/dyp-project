package com.sise.ccj.websocket.message.handle;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.socket.WebSocketSession;

public interface MessageHandle {
    void handle(WebSocketSession session, JSONObject msg);

}
