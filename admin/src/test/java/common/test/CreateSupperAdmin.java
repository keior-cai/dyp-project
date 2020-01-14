package common.test;

import com.mongodb.client.MongoCollection;
import com.sise.ccj.admin.AdminApplication;
import com.sise.ccj.constant.MongoDbConstant;
import com.sise.ccj.pojo.common.DypUserConnection;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class CreateSupperAdmin {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void createSupperAdmin(){
//        mongoTemplate.createCollection(DypUserConnection.class);
        DypUserConnection dypUserConnection = new DypUserConnection();
        dypUserConnection.setName("admin");
        dypUserConnection.setUserName("dddd");
        dypUserConnection.setPassword("123456");
        dypUserConnection.setCreateTime(new Date());
        dypUserConnection.setRole(false);
        dypUserConnection.setDeleted(false);
        mongoTemplate.insert(dypUserConnection, MongoDbConstant.DYP_USER_COLLECTION);
    }

    @Test
    public void findAllUser(){
        Query query = new Query();
        List<DypUserConnection> dypUserConnections =  mongoTemplate.find(query, DypUserConnection.class, MongoDbConstant.DYP_USER_COLLECTION);
        log.info("{}", dypUserConnections);
    }

    @Test
    public void updateUser(){
        mongoTemplate.updateFirst(Query.query(Criteria.where("userName").is("admin")), Update.update("password", "123456"), MongoDbConstant.DYP_USER_COLLECTION);
    }
}
