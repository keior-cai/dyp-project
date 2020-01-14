package com.sise.ccj.service;

import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.pojo.common.DypUserConnection;
import com.sise.ccj.request.admin.AdminRequest;

import java.util.List;

/**
 * @ClassName AdminService
 * @Description
 * @Author CCJ
 * @Date 2020/1/14 0:26
 **/
public interface AdminService {

    List<DypUserConnection> queryAdmin(AdminRequest param);

    void updateAdmin();

    void deleteAdmin(String adminId);

    void addAdmin(AdminRequest param);
}
