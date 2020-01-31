package com.sise.ccj.mapper;

import com.github.pagehelper.Page;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.request.admin.CustomerRequest;

public interface CustomerMapper {

    Page<CustomerPO> queryCustomer(CustomerRequest param);

    CustomerPO queryUserById(String openId);

    void insertUpdate(CustomerPO customerPO);
}
