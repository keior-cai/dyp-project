package com.sise.ccj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.constant.MongoDbConstant;
import com.sise.ccj.enums.DeletedEnum;
import com.sise.ccj.enums.admin.AdminRoleEnums;
import com.sise.ccj.pojo.common.DypUserConnection;
import com.sise.ccj.request.BaseRequest;
import com.sise.ccj.request.admin.AdminRequest;
import com.sise.ccj.service.AdminService;
import com.sise.ccj.utils.Pair;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

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
    public List<DypUserConnection> queryAdmin(AdminRequest param) {
        Criteria criteria = Criteria.where(DypUserConnection.Field.ROLE)
                .is(AdminRoleEnums.GENERAL_ADMIN.isRole())
                .and(DypUserConnection.Field.DELETED)
                .is(DeletedEnum.NOT_DELETED.isDelete());

        if (param.getUserName() != null) {
            criteria = Criteria.where(DypUserConnection.Field.USER_NAME)
                    .regex(BaseRequest.getLikeField(param.getUserName()));
        }
        Pair<Long, Integer> pair = param.getPageable();
        return mongoTemplate.find(Query.query(criteria)
                .skip(pair.getFirst())
                .limit(pair.getSecond()),
                DypUserConnection.class,
                MongoDbConstant.DYP_USER_COLLECTION);


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
