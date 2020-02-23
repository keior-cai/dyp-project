package com.sise.ccj.admin.controller;

import com.github.pagehelper.StringUtil;
import com.sise.ccj.annotation.AccessRolePermission;
import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.RedisConstant;
import com.sise.ccj.enums.admin.AdminRoleEnums;
import com.sise.ccj.mapper.LogMapper;
import com.sise.ccj.mapper.OrderStaticsMapper;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.pojo.common.OrderStaticsPO;
import com.sise.ccj.request.admin.AdminRequest;
import com.sise.ccj.service.AdminService;
import com.sise.ccj.service.StaticsCustomerService;
import com.sise.ccj.utils.Maps;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    private RedisUtil redisUtil;

    @Autowired
    private AdminService adminService;

    @Autowired
    private StaticsCustomerService staticsCustomerService;

    @Autowired
    private OrderStaticsMapper orderStaticsMapper;

    @Autowired
    private LogMapper logMapper;

    @PostMapping("/addAdmin")
    public HttpBody addAdmin(@RequestBody AdminRequest param){
        adminService.addAdmin(param);
        return HttpBody.SUCCESS;
    }


    @GetMapping("/queryAdmin")
    @AccessRolePermission
    public HttpBody queryAdmin(AdminRequest param){
        param.check();
        return HttpBody.getSucInstance(adminService.queryAdmin(param));
    }

    @PostMapping("/insertUpdate")
    public HttpBody insertUpdate(@RequestBody UserPO userPO){
        adminService.insertUpdate(userPO);
        return HttpBody.SUCCESS;
    }


    @PostMapping("/deleteAdmin/{adminId}")
    @AccessRolePermission
    public HttpBody deleteAdmin(@PathVariable Integer adminId){
        adminService.deleteAdmin(adminId);
        return HttpBody.SUCCESS;
    }

    @PostMapping("/updateAdmin")
    public HttpBody updateAdmin(@RequestBody AdminRequest param){
        adminService.updateAdmin(param);
        return HttpBody.SUCCESS;
    }

    @PostMapping("/updateInfo")
    public HttpBody updateInfo(){
        return HttpBody.SUCCESS;
    }

    @GetMapping("/getAdminInfo")
    public HttpBody getAdminInfo(){
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        return HttpBody.getSucInstance(userPO);
    }

    @GetMapping("/getCount")
    public HttpBody getCount(){
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        int count = 0;
        double total = 0;
        if (userPO.getRole() == AdminRoleEnums.SUPER_ADMIN.getRole()) {
            Map<Object, Object> countMap  = redisUtil.entries(RedisConstant.ORDER_COUNT);
            Map<Object, Object> map  = redisUtil.entries(RedisConstant.STATICS_CUSTOMER_COUNT);
            Map<Object, Object> totalMap  = redisUtil.entries(RedisConstant.ORDER_TOTAL);
            count = countMap.values().stream().mapToInt(e -> {
                if (!StringUtil.isEmpty(e+"") && !(e+"").equals("null")){
                    return Integer.parseInt(e + "");
                }
                return 0;
            }).sum();
            total += totalMap.values().stream().mapToDouble(e ->{
                if (!StringUtil.isEmpty(e+"") && !(e+"").equals("null")){
                    return Double.parseDouble(e + "");
                }
                return 0.00d;
            }).sum();
        }else {
            String countStr = redisUtil.hmGet(RedisConstant.STATICS_CUSTOMER_COUNT, userPO.getId()+"")+"";
            if (!StringUtil.isEmpty(countStr) && !countStr.equals("null")){
                count  = Integer.parseInt(countStr);
            }
            String totalStr = redisUtil.hmGet(RedisConstant.ORDER_TOTAL, userPO.getId()+"")+"";
            if (!StringUtil.isEmpty(totalStr ) && !totalStr.equals("null")){
                total = Double.parseDouble(totalStr);
            }
        }
        return HttpBody.getSucInstance(Maps.of("total", total, "fail", 0, "count",count));
    }

    @GetMapping("/getStatics")
    public HttpBody getStatics(){
        return HttpBody.getSucInstance(staticsCustomerService.queryStatics());
    }


    @PostMapping("/activeAdmin/{id}")
    public HttpBody activeAdmin(@PathVariable Integer id){
        adminService.activeAdmin(id);
        return HttpBody.SUCCESS;
    }

    @GetMapping("/queryLog")
    public HttpBody queryLog(){
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        return HttpBody.getSucInstance(logMapper.selectLogByUserId(userPO.getTableSpace(), userPO.getId()));
    }

    @GetMapping("/getCharts")
    public HttpBody getCharts(){
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        OrderStaticsPO orderStaticsPO = new OrderStaticsPO();
        orderStaticsPO.setDbPrefix(userPO.getTableSpace());
        orderStaticsPO.setYId(userPO.getId());
        return HttpBody.getSucInstance(orderStaticsMapper.queryPageGroup(orderStaticsPO));
    }

}
