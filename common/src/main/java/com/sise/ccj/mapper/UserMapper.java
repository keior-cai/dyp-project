package com.sise.ccj.mapper;

import com.github.pagehelper.Page;
import com.sise.ccj.pojo.admin.UserPO;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    UserPO queryUserById(@Param("id") Integer id);

    Page<UserPO> queryUser();

    void updateUser(UserPO userPO);

    void deleteUser(@Param("id") Integer id);

    int addUser(UserPO userPO);

    UserPO queryUserByNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    Page<UserPO> queryGeneralUser(@Param("userName") String userName, @Param("startTime") String startTime, @Param("endTime") String endTime);

    void insertUpdate(UserPO userPO);
}
