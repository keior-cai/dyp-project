package com.sise.common.auth;

import lombok.Data;

@Data
public class SiseAuth {
    private String uniqueKey;

    private String sessionId;
}
