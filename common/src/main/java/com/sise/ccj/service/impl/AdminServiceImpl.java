package com.sise.ccj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AdminServiceImpl
 * @Description
 * @Author CCJ
 * @Date 2020/1/14 0:27
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<JSONObject> queryAdmin() {
        return null;
    }

    @Override
    public void updateAdmin() {

    }

    @Override
    public void deleteAdmin() {

    }

    @Override
    public void addAdmin() {

    }
}
