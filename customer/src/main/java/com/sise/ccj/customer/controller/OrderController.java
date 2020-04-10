package com.sise.ccj.customer.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.StringUtil;
import com.sise.ccj.annotation.AccessAuthority;
import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.customer.config.CustomerConfig;
import com.sise.ccj.exception.ServerException;
import com.sise.ccj.mapper.DypDbMapper;
import com.sise.ccj.mapper.OrderMapper;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.pojo.common.OrderPO;
import com.sise.ccj.request.OrderRequest;
import com.sise.ccj.service.OrderService;
import com.sise.ccj.utils.Maps;
import com.sise.ccj.utils.QrCodeUtil;
import com.sise.ccj.utils.RSAEncrypt;
import com.sise.ccj.vo.BaseVO;
import com.sise.ccj.vo.HttpBody;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

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

    @Autowired
    private DypDbMapper dypDbMapper;

    @Autowired
    private CustomerConfig customerConfig;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping("/insertUpdate")
    public HttpBody insertUpdate(@RequestBody OrderPO param) {
        CustomerPO logPo = SessionContextHolder.getAccountAndValid(null);
        param.setOpenId(logPo.getOpenId());
        param.setYId(logPo.getYId());
        return HttpBody.getSucInstance(orderService.insertUpdate(param, logPo.getTableSpace()));
    }

    @GetMapping("/queryOrder")
    public HttpBody queryOrder(OrderRequest param) {
        CustomerPO logPo = SessionContextHolder.getAccountAndValid(null);
        param.setOpenId(logPo.getOpenId());
        List<String> dbs = dypDbMapper.queryDb();
        List<OrderPO> orderPOS = new LinkedList<>();
        long size = 0;
        for (String str : dbs) {
            if (str.equals("dyp_business")) continue;
            BaseVO<OrderPO> baseVO = orderService.queryOrder(param, str);
            if (baseVO.getTotal() >= 0) {
                List<OrderPO> poList = baseVO.getDetails();
                poList.forEach(e -> {
                    if (e.getStatus() == 0) {
                        long outTime = DateUtils.addMinutes(e.getCreateTime(), customerConfig.getTimeout()).getTime() - System.currentTimeMillis();
                        e.setOutTime(outTime);
                    }
                });
                orderPOS.addAll(poList);
                size += baseVO.getTotal();
            }
        }
        return HttpBody.getSucInstance(Maps.of("details", orderPOS, "total", size));
    }


    @PostMapping("/payOrder")
    public HttpBody payOrder(@RequestBody JSONObject json) {
        CustomerPO logPo = SessionContextHolder.getAccountAndValid(null);
        String dbPrefix;
        if (!StringUtils.isEmpty(json.getString("yId"))) {
            dbPrefix = CommonConstant.TABLE_SPACE.replace(CommonConstant.TABLE_SPACE_ID, json.getString("yId"));
        } else {
            dbPrefix = logPo.getDbPrefix();
        }
        if (StringUtil.isEmpty(logPo.getPayPassword())) {
            throw new ServerException("请设置支付密码");
        }
        if (!json.getString("payPassword").equals(logPo.getPayPassword())) {
            throw new ServerException("支付密码错误");
        }
        if (StringUtils.isEmpty(json.getString("orderSn"))) {
            throw new ServerException("订单号为空");
        }
        OrderPO orderPO = new OrderPO();
        orderPO.setDbPrefix(dbPrefix);
        orderPO.setOrderSn(json.getString("orderSn"));
        orderPO.setStatus(1);
        orderService.updateOrder(orderPO);
        return HttpBody.SUCCESS;
    }

    @GetMapping("/queryByOrderSn")
    public HttpBody qrCode(@RequestParam("orderSn") String orderSn, @RequestParam("yId") String yId) {
        String dbPrefix = CommonConstant.TABLE_SPACE.replace(CommonConstant.TABLE_SPACE_ID, yId);
        OrderPO orderPO = orderMapper.queryOrderByOrderSn(dbPrefix, orderSn);
        return HttpBody.getSucInstance(orderPO);
    }

    @GetMapping("/qrCode/{yId}/{orderSn}")
    @AccessAuthority
    public void qrCode(@PathVariable(value = "yId") String yId, @PathVariable(value = "orderSn") String orderSn, HttpServletResponse response) throws Exception {
//        String dbPrefix = CommonConstant.TABLE_SPACE.replace(CommonConstant.TABLE_SPACE_ID, yId);
//        OrderPO orderPO = orderMapper.queryOrderByOrderSn(dbPrefix, orderSn);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        QrCodeUtil.createQrCode(RSAEncrypt.encrypt(orderSn, customerConfig.getRsaPublic()), 300, 300, response);
    }

    @PostMapping("/cancelOrder/{orderSn}/{yId}")
    public HttpBody cancelOrder(@PathVariable String orderSn, @PathVariable String yId) {
        String dbPrefxi = CommonConstant.TABLE_SPACE.replace(CommonConstant.TABLE_SPACE_ID, yId);
        OrderPO orderPO = new OrderPO();
        orderPO.setDbPrefix(dbPrefxi);
        orderPO.setOrderSn(orderSn);
        orderPO.setStatus(6);
        orderService.updateOrder(orderPO);
        return HttpBody.SUCCESS;
    }
}
