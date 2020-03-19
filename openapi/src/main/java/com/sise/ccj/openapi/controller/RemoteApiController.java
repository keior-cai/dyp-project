package com.sise.ccj.openapi.controller;

import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.exception.ServerException;
import com.sise.ccj.mapper.OrderMapper;
import com.sise.ccj.mapper.UserMapper;
import com.sise.ccj.openapi.config.OpenApiConfig;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.pojo.common.OrderPO;
import com.sise.ccj.service.OrderService;
import com.sise.ccj.utils.RSAEncrypt;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RemoteApiController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OpenApiConfig openApiConfig;

    @Autowired
    private OrderService orderService;


    @PostMapping("/{token}/createOrder")
    public HttpBody createOrder(@PathVariable String token, @RequestBody OrderPO param) {
        UserPO userPO = userMapper.queryUserPoByToken(token);
        if (userPO == null) {
            throw new ServerException("用户不存在");
        }
        String tableSpace = CommonConstant.TABLE_SPACE.replace(CommonConstant.TABLE_SPACE_ID, userPO.getId() + "");
        param.setYId(userPO.getId());
        param.setOpenId(userPO.getToken());
        orderService.insertUpdate(param, tableSpace);
        return HttpBody.SUCCESS;
    }


    @GetMapping("/{token}/printOrder")
    public HttpBody printOrder(@PathVariable String token) throws Exception {
        String key = RSAEncrypt.decrypt(token, openApiConfig.getRsaPrivate());
        String[] keys = key.split("-");
        String yId = keys[0];
        String orderSn = keys[1];
        // 打印订单
        return HttpBody.SUCCESS;
    }

    @GetMapping("/{token}/getKey")
    public HttpBody getPublicKey(@PathVariable String token) {
        return HttpBody.getSucInstance(openApiConfig.getRsaPublic());
    }
}
