package com.sise.ccj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sise.ccj.mapper.CustomerMapper;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.request.admin.CustomerRequest;
import com.sise.ccj.service.CustomerService;
import com.sise.ccj.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public BaseVO queryCustomer(CustomerRequest param) {
        PageHelper.startPage(param.getPage(), param.getSize());
        Page<CustomerPO> customerPOS = customerMapper.queryCustomer(param);
        return BaseVO.builder(customerPOS);
    }

    @Override
    public void insertUpdate(CustomerPO customerPO) {
        customerMapper.insertUpdate(customerPO);
    }

    @Override
    public CustomerPO queryByOpenId(String openId) {
        return customerMapper.queryUserByOpenId(openId);
    }
}
