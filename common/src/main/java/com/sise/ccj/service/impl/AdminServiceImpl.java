package com.sise.ccj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sise.ccj.enums.DeletedEnum;
import com.sise.ccj.enums.admin.AdminRoleEnums;
import com.sise.ccj.enums.admin.AdminStatus;
import com.sise.ccj.mapper.UserMapper;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.request.admin.AdminRequest;
import com.sise.ccj.service.AdminService;
import com.sise.ccj.vo.BaseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AdminServiceImpl
 * @Description
 * @Author CCJ
 * @Date 2020/1/14 0:27
 **/
@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseVO
    queryAdmin(AdminRequest param) {
        PageHelper.startPage(param.getPage(), param.getSize());
        Page<UserPO> userPOS = userMapper.queryGeneralUser(param.getUserName(), param.getStartTime(), param.getEndTime());
        return BaseVO.builder(userPOS);
    }

    @Override
    public void updateAdmin(AdminRequest param) {
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(param, userPO);
        userMapper.updateUser(userPO);
    }

    @Override
    public void deleteAdmin(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    public void addAdmin(AdminRequest param) {
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(param, userPO);
        userPO.setRole(AdminRoleEnums.GENERAL_ADMIN.getRole());
        userPO.setDeleted(DeletedEnum.NOT_DELETED.getIsDelete());
        userMapper.addUser(userPO);
    }

    @Override
    public void activeAdmin(Integer id) {
        UserPO userPO = new UserPO();
        userPO.setId(id);
        userPO.setStatus(AdminStatus.ACTIVE.name());
        userMapper.updateUser(userPO);
    }

    @Override
    public void insertUpdate(UserPO userPO) {
        userMapper.insertUpdate(userPO);
    }
}
