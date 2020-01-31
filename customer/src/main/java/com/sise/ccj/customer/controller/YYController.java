package com.sise.ccj.customer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.StringUtil;
import com.sise.ccj.enums.admin.AdminStatus;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.request.admin.AdminRequest;
import com.sise.ccj.service.AdminService;
import com.sise.ccj.vo.BaseVO;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * @ClassName YYController
 * @Description
 * @Author CCJ
 * @Date 2020/1/29 15:36
 **/
@RestController
@RequestMapping("/yy")
public class YYController {



    @Autowired
    private AdminService adminService;




    @GetMapping("/queryYY")
    public HttpBody queryYY(AdminRequest param){
        BaseVO baseVO = adminService.queryAdmin(param);
        Object object = baseVO.getDetails().stream()
                .filter(e->{
                        if (((UserPO)e).getStatus().equals(AdminStatus.ACTIVE.name()) &&
                                StringUtil.isEmpty(param.getName())){
                            return true;
                        }else if (((UserPO)e).getStatus().equals(AdminStatus.ACTIVE.name()) &&
                                !StringUtil.isEmpty(param.getName())){
                            return JSON.parseObject(((UserPO) e).getInfo())
                                    .getString("name")
                                    .contains(param.getName());
                        }
                        return false;
                }).map(e-> {
                    JSONObject json = JSON.parseObject(((UserPO)e).getInfo());
                    json.put("id", ((UserPO) e).getId());
                    return json;
                })
                .collect(Collectors.toList());
        return HttpBody.getSucInstance(object);
    }
}
