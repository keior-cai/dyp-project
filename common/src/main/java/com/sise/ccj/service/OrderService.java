package com.sise.ccj.service;

import com.sise.ccj.pojo.common.OrderPO;
import com.sise.ccj.request.OrderRequest;
import com.sise.ccj.vo.BaseVO;

public interface OrderService {

    void addOrder();

    BaseVO<OrderPO> queryOrder(OrderRequest param, String dbPrefix);

    String insertUpdate(OrderPO orderPO, String dbPrefix);

    void updateOrder(OrderPO orderPO);
}
