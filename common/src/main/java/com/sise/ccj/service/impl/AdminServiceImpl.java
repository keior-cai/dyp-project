package com.sise.ccj.service.impl;

import com.sise.ccj.constant.MongoDbConstant;
import com.sise.ccj.enums.DeletedEnum;
import com.sise.ccj.enums.admin.AdminRoleEnums;
import com.sise.ccj.pojo.common.DypUserConnection;
import com.sise.ccj.request.BaseRequest;
import com.sise.ccj.request.admin.AdminRequest;
import com.sise.ccj.service.AdminService;
import com.sise.ccj.utils.Pair;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
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

    private static final List<String> collections = Arrays.asList("dyp_${bid}_move", "");

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
    public void deleteAdmin(String adminId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where(DypUserConnection.Field.ID)
                        .is(new ObjectId(adminId))),
                Update.update(DypUserConnection.Field.DELETED, DeletedEnum.DELETED),
                MongoDbConstant.DYP_USER_COLLECTION);
    }

    @Override
    public void addAdmin(AdminRequest param) {
        DypUserConnection dypUserConnection = new DypUserConnection();
        Date date = new Date();
        BeanUtils.copyProperties(param, dypUserConnection);
        dypUserConnection.setRole(AdminRoleEnums.GENERAL_ADMIN.isRole());
        dypUserConnection.setCreateTime(date);
        dypUserConnection.setUpdateTime(date);
        dypUserConnection.setDeleted(DeletedEnum.NOT_DELETED.isDelete());
        mongoTemplate.insert(dypUserConnection, MongoDbConstant.DYP_USER_COLLECTION);
        createCollections(dypUserConnection.getId());
    }

    private void createCollections(ObjectId id){
        String dypId = id.toString();
        collections.forEach(e -> {
            if (!mongoTemplate.collectionExists(e.replace(MongoDbConstant.SPLIT, dypId))){
                mongoTemplate.createCollection(e.replace(MongoDbConstant.SPLIT, dypId));
            }
        });
    }
}
