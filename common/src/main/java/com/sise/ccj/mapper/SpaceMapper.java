package com.sise.ccj.mapper;


import com.github.pagehelper.Page;
import com.sise.ccj.pojo.admin.SpacePO;
import org.apache.ibatis.annotations.Param;


public interface SpaceMapper {
    SpacePO querySpaceById(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

    Page<SpacePO> querySpace(@Param("dbPrefix") String dbPrefix);

    void addSpace(SpacePO spacePO);

    void delSpace(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

    void updateSpace(SpacePO spacePO);
}
