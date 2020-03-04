package com.sise.ccj.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DypDbMapper {

    List<String> queryDb();

    void executeSql(@Param("sql") String sql);
}
