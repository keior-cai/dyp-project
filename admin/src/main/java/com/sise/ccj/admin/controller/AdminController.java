package com.sise.ccj.admin.controller;

import com.sise.ccj.request.admin.AdminRequest;
import com.sise.ccj.service.AdminService;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public HttpBody addAdmin(@RequestBody AdminRequest param){
        adminService.addAdmin(param);
        return HttpBody.SUCCESS;
    }


    @GetMapping("/queryAdmin")
    public HttpBody queryAdmin(AdminRequest param){
        param.check();
        return HttpBody.getSucInstance(adminService.queryAdmin(param));
    }


    @PostMapping("/deleteAdmin/{adminId}")
    public HttpBody deleteAdmin(@PathVariable String adminId){
        adminService.deleteAdmin(adminId);
        return HttpBody.SUCCESS;
    }

    @PostMapping("/updateAdmin")
    public HttpBody updateAdmin(@RequestBody AdminRequest param){
        adminService.updateAdmin();
        return HttpBody.SUCCESS;
    }

    @PostMapping("/updateInfo")
    public HttpBody updateInfo(){
        return HttpBody.SUCCESS;
    }

    @GetMapping("/getAdminInfo")
    public HttpBody getAdminInfo(){
        return HttpBody.getSucInstance(new Object());
    }

}
