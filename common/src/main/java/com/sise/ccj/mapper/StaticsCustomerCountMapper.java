package com.sise.ccj.mapper;

import com.sise.ccj.pojo.common.StaticsCustomerCountPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaticsCustomerCountMapper {

    List<StaticsCustomerCountPO> queryStaticsByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
