package com.sise.ccj.admin.controller;

import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.request.OrderRequest;
import com.sise.ccj.service.OrderService;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Description
 * @Author CCJ
 * @Date 2020/2/6 16:50
 **/
@RestController
@RequestMapping("/management/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/queryOrder")
    public HttpBody queryOrder(OrderRequest param){
        UserPO logPo =  SessionContextHolder.getAccountAndValid();
        return HttpBody.getSucInstance(orderService.queryOrder(param, logPo.getTableSpace()));
    }
}
