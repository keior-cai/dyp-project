package com.sise.ccj.service.impl;

import com.sise.ccj.constant.TimeConstant;
import com.sise.ccj.mapper.StaticsCustomerCountMapper;
import com.sise.ccj.pojo.common.StaticsCustomerCountPO;
import com.sise.ccj.service.StaticsCustomerService;
import com.sise.ccj.utils.DateHelper;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class StaticsCustomerServiceImpl implements StaticsCustomerService {


    @Autowired
    private StaticsCustomerCountMapper customerCountMapper;

    @Override
    public List<StaticsCustomerCountPO> queryStatics() {
        Date date = new Date();
        Date near7Date = DateUtils.addDays(date, -7);
        List<StaticsCustomerCountPO> customerCountPOS = customerCountMapper.queryStaticsByTime(DateHelper.toYYYY_MM_DD(near7Date),DateHelper.toYYYY_MM_DD(date));
        if (CollectionUtils.isEmpty(customerCountPOS)) {
            for (int i = 0 ;i < TimeConstant.SERVEN; i++) {
                Date now = DateUtils.addDays(date, i - TimeConstant.SERVEN);
                StaticsCustomerCountPO customerCountPO = new StaticsCustomerCountPO();
                customerCountPO.setCount(0);
                customerCountPO.setYear(now.getYear()+1900);
                customerCountPO.setMouth(now.getMonth()+1);
                customerCountPO.setDay(now.getDate());
                customerCountPOS.add(customerCountPO);
            }
        }
        return customerCountPOS;
    }
}
