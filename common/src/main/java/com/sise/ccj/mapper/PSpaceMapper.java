package com.sise.ccj.mapper;

import com.github.pagehelper.Page;
import com.sise.ccj.pojo.common.PSpacePO;
import com.sise.ccj.request.admin.PSpaceRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PSpaceMapper {

    Page<PSpacePO> queryPSpace(PSpaceRequest param);

    Page<PSpacePO> queryPSpaceAndName(PSpaceRequest param);

    List<PSpacePO> queryByDate(PSpacePO pSpacePO);

    void insertUpdate(PSpacePO pSpacePO);

    void delPSpace(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

    PSpacePO queryPSpaceById(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

}
