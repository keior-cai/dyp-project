package com.sise.ccj.customer.controller;

import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.vo.HttpBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CustomerController
 * @Description
 * @Author CCJ
 * @Date 2020/2/6 0:04
 **/
@RestController
@RequestMapping("/my")
public class CustomerController {

    @GetMapping("/info")
    public HttpBody info(){
        return HttpBody.getSucInstance(SessionContextHolder.getAccountAndValid(null));
    }
}
