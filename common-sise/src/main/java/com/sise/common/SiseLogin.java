package com.sise.common;

import org.jsoup.nodes.Document;
import org.springframework.util.MultiValueMap;

public interface SiseLogin {

    String getKey(String sno);

    SiseUserInfo login(String username, String password);

    Document getDocument(String url, String sno);

    Document getDocument(String url, MultiValueMap<String, Object> body, String sno);

    SiseUserInfo loginForKey(String key);
}
