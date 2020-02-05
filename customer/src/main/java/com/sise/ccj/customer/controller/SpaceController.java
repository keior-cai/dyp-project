package com.sise.ccj.customer.controller;

import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.request.admin.PSpaceRequest;
import com.sise.ccj.service.PSpaceService;
import com.sise.ccj.vo.BaseVO;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SpaceController
 * @Description
 * @Author CCJ
 * @Date 2020/2/3 1:11
 **/
@RestController
@RequestMapping("/space")
public class SpaceController {

    @Autowired
    private PSpaceService pSpaceService;


    @GetMapping("/queryPspace")
    public HttpBody queryPspace(PSpaceRequest param){
        CustomerPO customerPO = SessionContextHolder.getAccountAndValid(null);
        BaseVO baseVO = pSpaceService.queryPSpace(param, customerPO.getTableSpace());
        return HttpBody.getSucInstance(baseVO);
    }
}
