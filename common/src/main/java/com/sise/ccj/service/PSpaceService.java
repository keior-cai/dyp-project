package com.sise.ccj.service;

import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.pojo.common.PSpacePO;
import com.sise.ccj.request.admin.PSpaceRequest;
import com.sise.ccj.vo.BaseVO;

public interface PSpaceService {


    BaseVO queryPSpace(PSpaceRequest param, UserPO loginPO);


    void insertUpdate(UserPO loginPO, PSpacePO param);

}
