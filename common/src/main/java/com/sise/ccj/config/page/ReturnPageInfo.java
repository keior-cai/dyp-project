package com.sise.ccj.config.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum  ReturnPageInfo {

    ALWAYS("always"),
    NONE("none");

    private String type;
}
