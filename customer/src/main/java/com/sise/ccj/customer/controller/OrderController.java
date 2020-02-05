package com.sise.ccj.customer.controller;

import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.pojo.common.OrderPO;
import com.sise.ccj.service.OrderService;
import com.sise.ccj.utils.OrderSnUtils;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Description
 * @Author CCJ
 * @Date 2020/2/5 16:20
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/insertUpdate")
    public HttpBody insertUpdate(@RequestBody OrderPO param){
        CustomerPO logPo = SessionContextHolder.getAccountAndValid(null);
        param.setOpenId(logPo.getOpenId());
        orderService.insertUpdate(param, logPo.getTableSpace());
        return HttpBody.SUCCESS;
    }
}
