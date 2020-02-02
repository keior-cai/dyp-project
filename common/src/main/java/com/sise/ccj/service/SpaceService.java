package com.sise.ccj.service;

import com.sise.ccj.pojo.admin.SpacePO;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.request.admin.SpaceRequest;
import com.sise.ccj.vo.BaseVO;

import java.util.List;

public interface SpaceService {
    SpacePO querySpaceById(Integer id,UserPO loginPo);

    BaseVO querySpace(SpaceRequest param, UserPO loginPo);

    void addSpace(SpacePO spacePO,UserPO loginPo);

    void delSpace(UserPO loginPo,Integer id);

    void updateSpace(SpacePO spacePO, UserPO loginPo);

    void insertUpdate(SpacePO spacePO, UserPO loginPo);

    List<SpacePO> loadSpace(String dbPrefix);
}
