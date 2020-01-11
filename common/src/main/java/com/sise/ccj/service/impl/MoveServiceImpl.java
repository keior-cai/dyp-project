package com.sise.ccj.service.impl;

import com.sise.ccj.constant.pojo.MongoBaseFieldConstant;
import com.sise.ccj.constant.pojo.MoveConstant;
import com.sise.ccj.pojo.Move;
import com.sise.ccj.request.move.MoveRequest;
import com.sise.ccj.service.MoveService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MoveServiceImpl
 * @Description
 * @Author CCJ
 * @Date 2019/12/29 15:29
 **/
@Service
public class MoveServiceImpl implements MoveService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addMove(MoveRequest move) {
        move.addCreateTimeAndUpdateTime();
        mongoTemplate.insert(move);
    }

    @Override
    public void delMove(ObjectId id) {
        mongoTemplate.remove(new Query(Criteria.where(MongoBaseFieldConstant.ID).is(id)));
    }

    @Override
    public List<Move> selectMove() {
        return mongoTemplate.findAll(Move.class);
    }

    @Override
    public Move findMoveById(ObjectId id) {
        return mongoTemplate.findOne(new Query(Criteria.where(MongoBaseFieldConstant.ID).is(id)), Move.class);
    }

    @Override
    public void updateMove(Object o) {
        Update update = new Update();
        update.set(MongoBaseFieldConstant.UPDATE_TIME, new Date());
        mongoTemplate.updateFirst(new Query(Criteria.where(MongoBaseFieldConstant.ID).is(o)),update
                ,Move.class);
    }
}
