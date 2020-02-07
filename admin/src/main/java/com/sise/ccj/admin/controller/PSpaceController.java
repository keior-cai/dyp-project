package com.sise.ccj.admin.controller;

import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.pojo.common.PSpacePO;
import com.sise.ccj.request.admin.PSpaceRequest;
import com.sise.ccj.service.PSpaceService;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PSpaceController
 * @Description
 * @Author CCJ
 * @Date 2020/2/2 16:15
 **/
@RestController
@RequestMapping("/management/pspace")
public class PSpaceController {


    @Autowired
    private PSpaceService pSpaceService;

    @GetMapping("/queryPSpace")
    public HttpBody queryPSpace(PSpaceRequest param){
        UserPO loginPo = SessionContextHolder.getLoginAccountInfo();
        param.setStatus(0);
        return HttpBody.getSucInstance(pSpaceService.queryPSpace(param, loginPo.getTableSpace()));
    }

    @PostMapping("/insertUpdate")
    public HttpBody insertUpdate(@RequestBody PSpacePO spacePO){
        UserPO loginPo = SessionContextHolder.getLoginAccountInfo();
        pSpaceService.insertUpdate(loginPo, spacePO);
        return HttpBody.SUCCESS;
    }

}
