package com.sise.ccj.service;

import com.github.pagehelper.Page;
import com.sise.ccj.pojo.admin.SpacePO;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.request.admin.SpaceRequest;
import com.sise.ccj.vo.BaseVO;

public interface SpaceService {
    SpacePO querySpaceById(Integer id,UserPO loginPo);

    BaseVO querySpace(SpaceRequest param, UserPO loginPo);

    void addSpace(SpacePO spacePO,UserPO loginPo);

    void delSpace(UserPO loginPo,Integer id);

    void updateSpace(SpacePO spacePO, UserPO loginPo);
}
