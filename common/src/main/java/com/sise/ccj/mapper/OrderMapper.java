package com.sise.ccj.mapper;

import com.github.pagehelper.Page;
import com.sise.ccj.pojo.common.OrderPO;
import com.sise.ccj.request.OrderRequest;

public interface OrderMapper {


    void insertUpdate(OrderPO orderPO);

    Page<OrderPO> queryOrder(OrderRequest param);
}
