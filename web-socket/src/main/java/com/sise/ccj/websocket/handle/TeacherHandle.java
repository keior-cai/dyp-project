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
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;


@Slf4j
@Component("/teacher")
public class TeacherHandle extends TextWebSocketHandler {


    @Autowired
    private Map<String, MessageHandle> msgHandleMap;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        JSONObject loginInfo = getLoginInfo(session);
        System.out.println(loginInfo);
        HandleCache.addTeacher(loginInfo.getString("teacherId"), loginInfo, session);
        System.out.println("---");
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        JSONObject msg = JSON.parseObject(message.getPayload());
        String type = msg.getString(WebSocketConstant.COMMAND);
        msgHandleMap.get(type).handle(session, msg);
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        ByteBuffer byteBuffer = message.getPayload();
        byte[] bytes = byteBuffer.array();
        JSONObject studentInfo = getLoginInfo(session);
        if (studentInfo == null) {
            return;
        }
        try {

            String studentToken = studentInfo.getString("studentId");
            if (HandleCache.isCall(studentToken)) {
                JSONObject msg = new JSONObject();
                msg.put("msg", "不在通话状态");
                TextMessage text = new TextMessage(msg.toJSONString());
                session.sendMessage(text);
            }
            String teacherToken = HandleCache.getCallToken(studentToken);
            Pair<JSONObject, WebSocketSession> pair = HandleCache.getTeacher(teacherToken);
            pair.getSecond().sendMessage(message);
            File file = new File("a.pcm");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        String token = getLoginInfoId(webSocketSession);
        if (!StringUtils.isEmpty(token)){
            HandleCache.delTeacher(token);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        String token = getLoginInfoId(webSocketSession);
        if (!StringUtils.isEmpty(token)){
            HandleCache.delTeacher(token);
        }
    }

    private JSONObject getLoginInfo(WebSocketSession session) {
        List<String> tokenList = session.getHandshakeHeaders().get("Cookie");
        if (CollectionUtils.isEmpty(tokenList)) {
            return null;
        }
        String token = tokenList.get(0).split("=")[1];
        return LoginCache.getTeacher(token);
    }

    private String getLoginInfoId(WebSocketSession session) {
        List<String> tokenList = session.getHandshakeHeaders().get("Cookie");
        if (CollectionUtils.isEmpty(tokenList)) {
            return null;
        }
        String token = tokenList.get(0).split("=")[1];
        return LoginCache.getTeacher(token).getString("teacherId");
    }


}
