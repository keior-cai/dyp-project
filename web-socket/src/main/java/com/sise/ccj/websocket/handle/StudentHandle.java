package com.sise.ccj.websocket.handle;

import com.sise.ccj.websocket.cache.HandleCache;
import com.sise.ccj.websocket.cache.LoginCache;
import com.sise.ccj.websocket.constants.WebSocketConstant;
import com.sise.ccj.websocket.message.handle.MessageHandle;
import com.sise.ccj.websocket.utils.Pair;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Component("/student")
public class StudentHandle extends TextWebSocketHandler {


    @Autowired(required = false)
    private Map<String, MessageHandle> msgHandleMap;

    private static Map<String, WebSocketSession> map = new ConcurrentHashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        map.put(session.getId(), session);
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
//        ByteBuffer byteBuffer = message.getPayload();
        System.out.println(message.getPayloadLength());
        for (Map.Entry<String, WebSocketSession> entry : map.entrySet()){
            try {
                entry.getValue().sendMessage(message);
            } catch (IOException e) {
                log.info("", e);
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        map.remove(webSocketSession.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        map.remove(webSocketSession.getId());
    }

    private JSONObject getLoginInfo(WebSocketSession session) {
        List<String> tokenList = session.getHandshakeHeaders().get("Cookie");
        if (CollectionUtils.isEmpty(tokenList)) {
            return null;
        }
        String token = tokenList.get(0).split("=")[1];
        return LoginCache.getStudent(token);
    }

    private String getLoginInfoId(WebSocketSession session) {
        List<String> tokenList = session.getHandshakeHeaders().get("Cookie");
        if (CollectionUtils.isEmpty(tokenList)) {
            return null;
        }
        String token = tokenList.get(0).split("=")[1];
        return LoginCache.getStudent(token).getString("teacherId");
    }
}
