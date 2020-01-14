package com.sise.ccj.admin.controller;

import com.sise.ccj.request.admin.AdminRequest;
import com.sise.ccj.service.AdminService;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * @ClassName AdminController
 * @Description
 * @Author CCJ
 * @Date 2020/1/14 0:18
 **/
@RestController
@RequestMapping("/management/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/addAdmin")
    public HttpBody addAdmin(){
        return HttpBody.SUCCESS;
    }


    @GetMapping("/queryAdmin")
    public HttpBody queryAdmin(AdminRequest param){
        param.check();
        return HttpBody.getSucInstance(adminService.queryAdmin(param));
    }


    @PostMapping("/deleteAdmin/{adminId}")
    public HttpBody deleteAdmin(@PathVariable String adminId){
        return HttpBody.SUCCESS;
    }

    @PostMapping("/updateAdmin")
    public HttpBody updateAdmin(){
        return HttpBody.SUCCESS;
    }

    @PostMapping("/updateInfo")
    public HttpBody updateInfo(){
        return HttpBody.SUCCESS;
    }

    @GetMapping("/getSupperAdminInfo")
    public HttpBody getSupperAdminInfo(){
        return HttpBody.getSucInstance(new Object());
    }

}
