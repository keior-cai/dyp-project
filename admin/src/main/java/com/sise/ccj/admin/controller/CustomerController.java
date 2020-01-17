package com.sise.ccj.admin.controller;

import com.sise.ccj.request.admin.CustomerRequest;
import com.sise.ccj.service.CustomerService;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/queryCustomer")
    public HttpBody queryCustomer(CustomerRequest param){
        return HttpBody.getSucInstance(customerService.queryCustomer(param));
    }
}
