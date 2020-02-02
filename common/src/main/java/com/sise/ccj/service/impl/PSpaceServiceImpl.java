package com.sise.ccj.service.impl;

import com.github.pagehelper.PageHelper;
import com.sise.ccj.exception.ServerException;
import com.sise.ccj.mapper.PSpaceMapper;
import com.sise.ccj.mapper.SpaceMapper;
import com.sise.ccj.pojo.admin.SpacePO;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.pojo.common.PSpacePO;
import com.sise.ccj.request.admin.PSpaceRequest;
import com.sise.ccj.service.PSpaceService;
import com.sise.ccj.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName PSpaceServiceImpl
 * @Description
 * @Author CCJ
 * @Date 2020/2/2 16:20
 **/
@Service
public class PSpaceServiceImpl implements PSpaceService {


    @Autowired
    private PSpaceMapper pSpaceMapper;

    @Autowired
    private SpaceMapper spaceMapper;


    @Override
    public BaseVO queryPSpace(PSpaceRequest param,UserPO loginPO) {
        PageHelper.startPage(param.getPage(), param.getSize());
        param.setDbPrefix(loginPO.getTableSpace());
        return BaseVO.builder(pSpaceMapper.queryPSpaceAndName(param));
    }

    @Override
    public void insertUpdate(UserPO loginPO, PSpacePO param) {
        SpacePO spacePO = spaceMapper.querySpaceById(loginPO.getTableSpace(), param.getSId());
        param.setDbPrefix(loginPO.getTableSpace());

        List<PSpacePO> spacePOList = pSpaceMapper.queryByDate(param);
        if (!CollectionUtils.isEmpty(spacePOList)){
            throw new ServerException("时间段冲突");
        }
        param.setInfo(spacePO.getInfo());
        param.setNum(spacePO.getTotal());
        pSpaceMapper.insertUpdate(param);
    }
}
