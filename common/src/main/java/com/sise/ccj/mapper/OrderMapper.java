package com.sise.ccj.mapper;

import com.github.pagehelper.Page;
import com.sise.ccj.pojo.common.OrderPO;
import com.sise.ccj.request.OrderRequest;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {


    void insertUpdate(OrderPO orderPO);

    Page<OrderPO> queryOrder(OrderRequest param);

    OrderPO queryOrderByOrderSn(@Param("dbPrefix") String dbPrefix, @Param("orderSn") String orderSn);
}
