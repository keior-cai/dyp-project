package com.sise.ccj.openapi.controller;

import com.sise.ccj.vo.HttpBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RemoteApiController {

    @PostMapping("/{token}/createOrder")
    public HttpBody createOrder(@PathVariable String token) {
        return HttpBody.SUCCESS;
    }
}
