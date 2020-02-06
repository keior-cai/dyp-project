package com.sise.ccj.customer.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.StringUtil;
import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.exception.ServerException;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.pojo.common.OrderPO;
import com.sise.ccj.request.OrderRequest;
import com.sise.ccj.service.OrderService;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        param.setYId(logPo.getYId());
        return HttpBody.getSucInstance(orderService
                .insertUpdate(param, logPo.getTableSpace())
        );
    }

    @GetMapping("/queryOrder")
    public HttpBody queryOrder(OrderRequest param){
        CustomerPO logPo = SessionContextHolder.getAccountAndValid(null);
        param.setOpenId(logPo.getOpenId());
        return HttpBody.getSucInstance(orderService.queryOrder(param, logPo.getTableSpace()));
    }


    @PostMapping("/payOrder")
    public HttpBody payOrder(@RequestBody JSONObject json){
        CustomerPO logPo = SessionContextHolder.getAccountAndValid(null);
        if (StringUtil.isEmpty(logPo.getPayPassword())){
            throw new ServerException("请设置支付密码");
        }
        if (!json.getString("payPassword").equals(logPo.getPayPassword())){
            throw new ServerException("支付密码错误");
        }
        OrderPO orderPO = new OrderPO();
        orderPO.setDbPrefix(logPo.getTableSpace());
        orderPO.setOrderSn(json.getString("orderSn"));
        orderPO.setStatus(1);
        orderService.updateOrder(orderPO);
        return HttpBody.SUCCESS;
    }



}
