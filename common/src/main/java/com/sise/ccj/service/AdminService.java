package com.sise.ccj.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @ClassName AdminService
 * @Description
 * @Author CCJ
 * @Date 2020/1/14 0:26
 **/
public interface AdminService {

    List<JSONObject> queryAdmin();

    void updateAdmin();

    void deleteAdmin();

    void addAdmin();
}
