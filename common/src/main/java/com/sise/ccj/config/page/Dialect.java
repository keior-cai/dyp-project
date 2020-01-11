package com.sise.ccj.config.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Dialect {

    MYSQL("mysql"),
    ORACLE("oracle");

    private String type;
}
