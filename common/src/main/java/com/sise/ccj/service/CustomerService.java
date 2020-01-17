package com.sise.ccj.service;

import com.sise.ccj.request.admin.CustomerRequest;
import com.sise.ccj.vo.BaseVO;

public interface CustomerService {
    BaseVO queryCustomer(CustomerRequest param);
}
