package com.sise.ccj.task;

import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.utils.Maps;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class StartCall {
    private static RestTemplate client = new RestTemplate();
    private static JSONObject body = new JSONObject();

    static {
        String[] str = {"你说什么", "你说啥", "你说什么东西", "啊你说什么", "啊你说什么啊", "啊你刚说啥", "你刚刚说啥", "你说了什么", "有点卡"};
        String[] str1 = {"没听见", "没听清", "没听到", "重复一遍", "重复一下", "重复下", "重复一次", "刚说啥", "刚说什么", "再说一遍", "刚才说啥", "刚才说什么", "听不清", "听不见", "没有听见", "没有听清", "信号不好", "再说一次", "再说一遍", "再说一下", "没太听见", "没太听清", "没太听到", "听不太清", "听不太见", "信号不太好"};
        String[] str2 = {"好", " 有 ", " 能 ", " 行 ", " 嗯 ", " 是 ", " 呃 ", " 对 ", " 恩 ", " 哦 ", " 噢 ", " 喔 ", " 不 ", " 否 ", " 错 ", " 啥 ", " 啊 ", " yeah ", " ok ", " okay ", " nope ", " yes ", " no ", " No ", " wrong "};
        body.put("templateId", 1);
        body.put("calloutNumber", "051485592859");
        body.put("sessionId", "1-1000-60");
        body.put("mobileNumber", "15218628770");
        body.put("requestIp", "10.8.0.173:9906");
        body.put("voiceRelativePath", "1-1000-4.zip"); //新的录音管理传入上线模板录音名称
        JSONObject json = new JSONObject();
        json.put("mockApiKey", "1-1000-1");
        json.put("publicKey", "AckSxjLBimBnI36Mk0VkFpV5iFrvsz8WHM/Gkh+iZBQ");
        json.put("templateName", "银行催收");
        JSONObject param = new JSONObject();
        param.put("asrConfig", Maps.of("manufacturerId", "aliyun"));
        param.put("ivrConfig", new JSONObject());
        JSONObject param2 = new JSONObject();
        param2.put("blackListforBreak", Collections.emptyList());
        param2.put("breakUserSpeechId", "");
        param2.put("breakUserSpeechText", "");
        param2.put("breakUserSpeechTime", -1);
        param2.put("breakable", true);
        param2.put("isBlackListforBreak", false);
        param2.put("isUndistinguished", true);
        param2.put("isUndistinguishedKeyWord", true);
        param2.put("isWhiteListforRequstYibot", true);
        param2.put("maxReplayTime", 5);
        param2.put("maxReplayTimePerTurn", 5);
        param2.put("notBreakTime", 500);
        param2.put("playMode", "playPureTts");
        param2.put("replayMode", "LastTurn");
        param2.put("replayText", Collections.emptyList());
        param2.put("replayVoice", Collections.emptyList());
        param2.put("replayforMuteTime", 5000);
        param2.put("unGetStrategy", "RequestYibot");
        param2.put("unGetTimesPerTurn", 5);
        param2.put("unGetTotalTimes", 5);
        param2.put("undistinguished", str);
        param2.put("undistinguishedKeyWord", str1);
        param2.put("userSpeechCommbinTime", 500);
        param2.put("whiteListforRequstYibot", str2);
        param2.put("ttsConfig", Maps.of("manufacturerId", "zhuiyi", "speed", 0.1, "voiceId", "lsbl", "volume", 0.1));
        param2.put("yibotConfig", Maps.of("defaultFailId", "", "defaultFailText", "", "privKey", "bba87507e80d1061195bcfb73007988c", "pubKey", "AckSxjLBimBnI36Mk0VkFpV5iFrvsz8WHM/Gkh+iZBQ", "templateName", "银行催收"));
        param.put("sysConfig", param2);
        json.put("callConfig", param);
        body.put("params", json);
    }

    public static void main(String[] args) {

        // {"kahao":"55221","qkje":"dd","gender":"ddd","zdhk":"dd","caseId":"ddd","name":"ddd","telNum":"15218628770"}
        System.out.println(body);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<JSONObject> entity = new HttpEntity<>(body, headers);
        client.postForEntity("http://172.16.20.33:51999/yicall/callout/startTask", entity, String.class);
        System.out.println();
    }
}
