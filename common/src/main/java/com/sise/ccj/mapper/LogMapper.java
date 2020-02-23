package com.sise.ccj.mapper;

import com.github.pagehelper.Page;
import com.sise.ccj.pojo.admin.LogPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper {
    void insertLog(LogPO logPO);

    Page<LogPO> queryLogByUserId(@Param("dbPrefix") String dbPrefix, @Param("userId") int userId);

    List<LogPO> selectLogByUserId(@Param("dbPrefix") String dbPrefix, @Param("userId") int userId);

}
