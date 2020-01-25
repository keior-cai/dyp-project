package com.sise.ccj.admin.controller;

import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.pojo.admin.SpacePO;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.request.admin.SpaceRequest;
import com.sise.ccj.service.SpaceService;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management/space")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @GetMapping("/querySpace")
    public HttpBody querySpace(SpaceRequest param){
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        return HttpBody.getSucInstance(spaceService.querySpace(param, userPO));
    }

    @PostMapping("/addSpace")
    public HttpBody addSpace(@RequestBody SpacePO spacePO){
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        spaceService.addSpace(spacePO, userPO);
        return HttpBody.SUCCESS;
    }

    @PostMapping("/delSpace/{id}")
    public HttpBody delSpace(@PathVariable Integer id){
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        spaceService.delSpace(userPO, id);
        return HttpBody.SUCCESS;
    }
    @PostMapping("/updateSpace")
    public HttpBody updateSpace(@RequestBody SpacePO spacePO){
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        spaceService.updateSpace(spacePO, userPO);
        return HttpBody.SUCCESS;
    }

    @PostMapping("/insertUpdate")
    public HttpBody insertUpdate(@RequestBody SpacePO spacePO){
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        spaceService.insertUpdate(spacePO, userPO);
        return HttpBody.SUCCESS;
    }

}
