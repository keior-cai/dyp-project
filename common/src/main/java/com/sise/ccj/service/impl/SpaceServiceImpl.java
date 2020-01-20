package com.sise.ccj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sise.ccj.mapper.SpaceMapper;
import com.sise.ccj.pojo.admin.SpacePO;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.request.admin.SpaceRequest;
import com.sise.ccj.service.SpaceService;
import com.sise.ccj.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceServiceImpl implements SpaceService {


    @Autowired
    private SpaceMapper spaceMapper;

    @Override
    public SpacePO querySpaceById(Integer id, UserPO loginPo) {
        return spaceMapper.querySpaceById(loginPo.getTableSpace(), id);
    }

    @Override
    public BaseVO querySpace(SpaceRequest param, UserPO loginPo) {
        PageHelper.startPage(param.getPage(), param.getSize());
        return BaseVO.builder(spaceMapper.querySpace(loginPo.getTableSpace()));
    }

    @Override
    public void addSpace(SpacePO spacePO, UserPO loginPo) {
        spacePO.setDbPrefix(loginPo.getTableSpace());
        spaceMapper.addSpace(spacePO);
    }

    @Override
    public void delSpace(UserPO loginPo, Integer id) {
        spaceMapper.delSpace(loginPo.getTableSpace(), id);
    }

    @Override
    public void updateSpace(SpacePO spacePO, UserPO loginPo) {
        spacePO.setDbPrefix(loginPo.getTableSpace());
        spaceMapper.updateSpace(spacePO);
    }
}
