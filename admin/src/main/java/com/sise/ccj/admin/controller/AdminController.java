package com.sise.ccj.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.sise.ccj.annotation.AccessRolePermission;
import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.RedisConstant;
import com.sise.ccj.enums.admin.AdminRoleEnums;
import com.sise.ccj.mapper.DypDbMapper;
import com.sise.ccj.mapper.LogMapper;
import com.sise.ccj.mapper.OrderMapper;
import com.sise.ccj.mapper.OrderStaticsMapper;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.pojo.common.OrderPO;
import com.sise.ccj.pojo.common.OrderStaticsPO;
import com.sise.ccj.request.OrderRequest;
import com.sise.ccj.request.admin.AdminRequest;
import com.sise.ccj.service.AdminService;
import com.sise.ccj.service.StaticsCustomerService;
import com.sise.ccj.utils.DateHelper;
import com.sise.ccj.utils.Maps;
import com.sise.ccj.vo.HttpBody;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    private StaticsCustomerService staticsCustomerService;

    @Autowired
    private OrderStaticsMapper orderStaticsMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private OrderStaticsMapper staticsMapper;

    @Autowired
    private DypDbMapper dbMapper;

    @PostMapping("/addAdmin")
    public HttpBody addAdmin(@RequestBody AdminRequest param) {
        adminService.addAdmin(param);
        return HttpBody.SUCCESS;
    }


    @GetMapping("/queryAdmin")
    @AccessRolePermission
    public HttpBody queryAdmin(AdminRequest param) {
        param.check();
        return HttpBody.getSucInstance(adminService.queryAdmin(param));
    }

    @PostMapping("/insertUpdate")
    public HttpBody insertUpdate(@RequestBody UserPO userPO) {
        adminService.insertUpdate(userPO);
        return HttpBody.SUCCESS;
    }


    @PostMapping("/deleteAdmin/{adminId}")
    @AccessRolePermission
    public HttpBody deleteAdmin(@PathVariable Integer adminId) {
        adminService.deleteAdmin(adminId);
        return HttpBody.SUCCESS;
    }

    @PostMapping("/updateAdmin")
    public HttpBody updateAdmin(@RequestBody AdminRequest param) {
        adminService.updateAdmin(param);
        return HttpBody.SUCCESS;
    }

    @PostMapping("/updateInfo")
    public HttpBody updateInfo() {
        return HttpBody.SUCCESS;
    }

    @GetMapping("/getAdminInfo")
    public HttpBody getAdminInfo() {
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        return HttpBody.getSucInstance(userPO);
    }

    @GetMapping("/getCount")
    public HttpBody getCount() {
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        int count = 0;
        double total = 0;
        OrderStaticsPO staticsPO = new OrderStaticsPO();
        staticsPO.setCreateTime(DateHelper.parseYYYY_MM_DD_HH_MM_SS(DateHelper.getTodayStartTime()));
        if (userPO.getRole() == AdminRoleEnums.SUPER_ADMIN.getRole()) {
            List<String> dbs = dbMapper.queryDb();
            for (String db : dbs) {
                if ("dyp_business".equals(db)) continue;
                staticsPO.setDbPrefix(db);
                JSONObject json = staticsMapper.queryPageGroup(staticsPO);
                if (json == null) {
                    continue;
                }
                total += json.getDoubleValue("total");
                count += json.getIntValue("count");
            }
        } else {
            staticsPO.setDbPrefix(userPO.getTableSpace());
            JSONObject json = staticsMapper.queryPageGroup(staticsPO);
            if (json != null) {
                count = json.getIntValue("count");
                total = json.getDoubleValue("total");
            }
        }
        return HttpBody.getSucInstance(Maps.of("total", total, "fail", 0, "count", count));
    }

    @GetMapping("/getStatics")
    public HttpBody getStatics() {
        return HttpBody.getSucInstance(staticsCustomerService.queryStatics());
    }


    @PostMapping("/activeAdmin/{id}")
    public HttpBody activeAdmin(@PathVariable Integer id) {
        adminService.activeAdmin(id);
        return HttpBody.SUCCESS;
    }

    @GetMapping("/queryLog")
    public HttpBody queryLog() {
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        if (userPO.getRole() == AdminRoleEnums.SUPER_ADMIN.getRole()) {
            return HttpBody.getSucInstance(Collections.emptyList());
        }
        return HttpBody.getSucInstance(logMapper.selectLogByUserId(userPO.getTableSpace(), userPO.getId()));
    }

    @GetMapping("/getCharts")
    public HttpBody getCharts() {
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        OrderStaticsPO orderStaticsPO = new OrderStaticsPO();
        orderStaticsPO.setDbPrefix(userPO.getTableSpace());
        orderStaticsPO.setYId(userPO.getId());
        return HttpBody.getSucInstance(orderStaticsMapper.queryPageGroup(orderStaticsPO));
    }

    @GetMapping("/getIndexCharts")
    public HttpBody getIndexCharts() {
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        OrderStaticsPO orderStaticsPO = new OrderStaticsPO();
        orderStaticsPO.setDbPrefix(userPO.getTableSpace());
        orderStaticsPO.setYId(userPO.getId());
        Date date = DateHelper.parseYYYY_MM_DD_HH_MM_SS(DateHelper.getTodayStartTime());
        List<JSONObject> list = new ArrayList<>();
        for (int i = 7; i >= 0; i--){
            Date tmp = DateUtils.addDays(date, -i);
            orderStaticsPO.setCreateTime(tmp);
            JSONObject json = orderStaticsMapper.queryPageGroup(orderStaticsPO);
            if (json == null){
                json = new JSONObject();
                json.put("total", 0);
                json.put("count", 0);
            }
            json.put("time", DateHelper.toYYYY_MM_DD(tmp));
            list.add(json);
        }
        return HttpBody.getSucInstance(list);
    }

    @GetMapping("/createToken")
    public HttpBody createToken() {
        String token = UUID.randomUUID().toString();
        return HttpBody.getSucInstance(token);
    }

    @GetMapping("/getStaticsd")
    public HttpBody getStaticsd(@RequestParam(value = "startTime", required = false) Date startTime,
                                @RequestParam(value = "endTime", required = false) Date endTime){
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        OrderStaticsPO orderStaticsPO = new OrderStaticsPO();
        orderStaticsPO.setCreateTime(startTime);
        orderStaticsPO.setUpdateTime(endTime);
        orderStaticsPO.setYId(userPO.getId());
        return HttpBody.getSucInstance(orderStaticsMapper.queryPageGroupByTime(orderStaticsPO));
    }


}
