package com.sise.ccj.mapper;


import com.github.pagehelper.Page;
import com.sise.ccj.pojo.admin.SpacePO;
import com.sise.ccj.request.admin.SpaceRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SpaceMapper {
    SpacePO querySpaceById(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

    Page<SpacePO> querySpace(SpaceRequest param);

    void addSpace(SpacePO spacePO);

    void delSpace(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

    void updateSpace(SpacePO spacePO);

    void insertUpdate(SpacePO spacePO);

    List<SpacePO> loadSpace(@Param("dbPrefix") String dbPrefix);
}
