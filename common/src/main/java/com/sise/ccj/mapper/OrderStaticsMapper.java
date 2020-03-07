package com.sise.ccj.mapper;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.sise.ccj.pojo.common.OrderStaticsPO;

import java.util.List;

public interface OrderStaticsMapper {
    void inserUpdate(OrderStaticsPO staticsPO);

    Page<OrderStaticsPO> queryPage(OrderStaticsPO orderStaticsPO);


    JSONObject queryPageGroup(OrderStaticsPO orderStaticsPO);

}
