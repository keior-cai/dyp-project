package com.sise.ccj.mapper;

import com.sise.ccj.pojo.common.OpenOrderPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OpenOrderMapper {

    void insertUpdate(OpenOrderPO openOrderPO);

    List<OpenOrderPO> queryList(@Param("dbPrefix") String dbPrefix, @Param("status") Integer status);
}
