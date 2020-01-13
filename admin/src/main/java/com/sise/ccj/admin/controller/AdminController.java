package com.sise.ccj.admin.controller;

import com.sise.ccj.vo.HttpBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/addAdmin")
    public HttpBody addAdmin(){
        return HttpBody.SUCCESS;
    }


    @GetMapping("/queryAdmin")
    public HttpBody queryAdmin(){
        return HttpBody.getSucInstance(Collections.emptyList());
    }


    @PostMapping("/deleteAdmin")
    public HttpBody deleteAdmin(){
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
