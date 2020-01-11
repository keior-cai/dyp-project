package com.sise.ccj.service;

import com.sise.ccj.pojo.Move;
import com.sise.ccj.request.move.MoveRequest;
import org.bson.types.ObjectId;

import java.util.List;

public interface MoveService {
    void addMove(MoveRequest move);

    void delMove(ObjectId id);

    List<Move> selectMove();

    Move findMoveById(ObjectId id);

    void updateMove(Object o);

}
