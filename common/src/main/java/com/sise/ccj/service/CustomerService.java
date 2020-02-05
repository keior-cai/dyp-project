package com.sise.ccj.service;

import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.request.admin.CustomerRequest;
import com.sise.ccj.vo.BaseVO;

public interface CustomerService {
    BaseVO queryCustomer(CustomerRequest param);

    void insertUpdate(CustomerPO customerPO);

    CustomerPO queryByOpenId(String openId);
}
