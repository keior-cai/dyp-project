package com.sise.common;

import org.jsoup.nodes.Document;
import org.springframework.util.MultiValueMap;

public interface SiseLogin {

    SiseUserInfo login(String username, String password);

    Document getDocument(String url, SiseUserInfo siseUserInfo);

    Document getDocument(String url, MultiValueMap<String, Object> body, SiseUserInfo userInfo);

    SiseUserInfo loginForKey(String key);

    SiseClass getSiseClass(SiseUserInfo siseUserInfo);
}
