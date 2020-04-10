package com.sise.ccj.mapper;

import com.github.pagehelper.Page;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.request.admin.CustomerRequest;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {

    Page<CustomerPO> queryCustomer(CustomerRequest param);

    CustomerPO queryUserById(@Param("id") Integer id);

    CustomerPO queryUserByOpenId(@Param("openId") String openId);

    void insertUpdate(CustomerPO customerPO);
}
