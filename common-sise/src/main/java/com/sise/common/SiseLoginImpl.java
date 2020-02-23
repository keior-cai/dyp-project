package com.sise.common;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Service
public class SiseLoginImpl implements SiseLogin {


    @Value("${sise.ipPort}")
    private String ipPort;

    private RestTemplate client = new RestTemplate();

    private static Map<String, String> keyMap = new ConcurrentHashMap<>();

    private static Map<String, String> sessionMp = new ConcurrentHashMap<>();


    @PostConstruct
    public void init() {
        if (StringUtils.isEmpty(ipPort)){
            ipPort = SiseLoginUrlConstant.defaultIpPort;
        }
        SiseLoginUrlConstant.loginUrl = SiseLoginUrlConstant.loginUrl.replace("${ipPort}", ipPort);
        SiseLoginUrlConstant.indexUrl = SiseLoginUrlConstant.indexUrl.replace("${ipPort}", ipPort);
        SiseLoginUrlConstant.userInfoUrl = SiseLoginUrlConstant.userInfoUrl.replace("${ipPort}", ipPort);
        SiseLoginUrlConstant.loginPageUrl = SiseLoginUrlConstant.loginPageUrl.replace("${ipPort}", ipPort);
    }

    @Override
    public String getKey(String sno) {
        return keyMap.get(sno);
    }

    @Override
    public SiseUserInfo login(String username, String password) {
        Document info = null;
        try {
            Pair<String, String> pair = getRanomdStr();
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("username", username);
            body.add("password", password);
            body.add(pair.getKey(), pair.getValue());
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, getHeader(null));
            ResponseEntity<String> response = client.exchange(SiseLoginUrlConstant.loginUrl, HttpMethod.POST, entity, String.class);
            HttpHeaders responseHead = response.getHeaders();
            entity = new HttpEntity<>(null, getHeader(getJSSESSIONID(responseHead, username)));
            response = client.exchange(SiseLoginUrlConstant.indexUrl, HttpMethod.GET, entity, String.class);
            Document document = Jsoup.parse(response.getBody());
            Elements elements = document.getElementsByClass("table1");
            String key = elements.first().child(0).child(0).child(0).child(0).child(0).child(0).child(0).attr("onclick").split("\\?")[1].replace("'", "");
            keyMap.put(username, key);
            info = getDocument(SiseLoginUrlConstant.userInfoUrl.replace("${key}", key), username);
        } catch (Exception e) {
            log.error("登录失败", e);
            throw new LoginException();
        }
        return getSiseUserInfo(info);
    }

    @Override
    public Document getDocument(String url, String sno) {
        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, getHttpEntity(null, getHeader(sessionMp.get(sno))), String.class);
        return Jsoup.parse(response.getBody());
    }

    @Override
    public Document getDocument(String url, MultiValueMap<String, Object> body, String sno) {
        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, getHttpEntity(body, getHeader(sessionMp.get(sno))), String.class);
        return Jsoup.parse(response.getBody());
    }


    public Pair<String, String> getRanomdStr(){
        ResponseEntity<String> response = client.exchange(SiseLoginUrlConstant.loginPageUrl, HttpMethod.GET, null, String.class);
        Document document = Jsoup.parse(response.getBody());
        Element element = document.getElementsByTag("input").first();
        return new Pair<>(element.attr("name"), element.attr("value"));
    }


    @Override
    public SiseUserInfo loginForKey(String key) {
        ResponseEntity<String> response = client.exchange(SiseLoginUrlConstant.userInfoUrl.replace("${key}", key), HttpMethod.GET, null, String.class);
        return getSiseUserInfo(Jsoup.parse(response.getBody()));
    }

    private HttpHeaders getHeader(String cookie) {
        HttpHeaders header = new HttpHeaders();
        header.setAccept(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.TEXT_HTML, MediaType.ALL, MediaType.APPLICATION_XHTML_XML));
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.add("Cookie", cookie);
        return header;
    }

    private HttpEntity<MultiValueMap<String, Object>> getHttpEntity(MultiValueMap<String, Object> body, HttpHeaders header) {
        return new HttpEntity<>(body, header);
    }

    private String getJSSESSIONID(HttpHeaders header, String sno) {
        String jsSessionId = header.get("Set-Cookie").get(0).split(";")[0];
        sessionMp.put(sno, jsSessionId);
        return jsSessionId;
    }


    private SiseUserInfo getSiseUserInfo(Document document) {
        if (document == null) {
            return null;
        }
        SiseUserInfo siseUserInfo = new SiseUserInfo();
        try{
            Elements elements = document.getElementsByClass("table1").first().children();
            Element child = elements.first().child(0).child(0).child(1).child(0);

            // 第一行信息
            siseUserInfo.setUsername(child.child(0).child(1).child(0).text());
            siseUserInfo.setName(child.child(0).child(3).child(0).text());
            siseUserInfo.setGrade(child.child(0).child(5).child(0).text());
            siseUserInfo.setSpecialty(child.child(0).child(7).child(0).text());
            // 第二行信息
            siseUserInfo.setIdCard(child.child(1).child(1).child(0).text());
            siseUserInfo.setEmail(child.child(1).child(3).child(0).text());
            Element ee = child.child(2).child(1);
            // 第三行信息
            siseUserInfo.setSClass(child.child(2).child(1).text());
            siseUserInfo.setTutor(child.child(2).child(3).text());
            siseUserInfo.setCounselor(child.child(2).child(5).text());
        } catch (Exception e){
            log.error("获取用户信息出错");
            throw new LoginException();
        }
        return siseUserInfo;
    }

}
