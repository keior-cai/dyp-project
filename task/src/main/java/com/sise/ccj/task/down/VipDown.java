package com.sise.ccj.task.down;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.sise.ccj.compant.SpringContext;
import com.sise.ccj.mapper.CustomerMapper;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.request.admin.CustomerRequest;
import com.sise.ccj.task.job.Job;
import com.sise.ccj.utils.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Vector;

@Slf4j
@Service("VipDownJob")
@DisallowConcurrentExecution
public class VipDown implements Job {

    @Autowired
    private CustomerMapper movieMapper;

    private static final List<Integer> downMap = new Vector<>();

    private static final List<Integer> finishMap = new Vector<>();

    private static final Object o = new Object();

    @Override
    public void execute(JobExecutionContext var1) {
        log.info("MovieDownJob load db = {}", finishMap);
        if (!downMap.isEmpty()) {
            return;
        }
        CustomerMapper customerMapper = SpringContext.getBeanByType(CustomerMapper.class);
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setIsVip(1);
        Page<CustomerPO> customerPOS = customerMapper.queryCustomer(customerRequest);
        List<CustomerPO> customerList = customerPOS.getResult();
        for (CustomerPO customerPO : customerList) {
            Date downTime = DateUtils.addMonths(customerPO.getVipCreateTime(), customerPO.getVipMouth());
            if (downTime.getTime() <= System.currentTimeMillis()) {
                downMap.add(customerPO.getId());
            }
        }
    }

    @Override
    public Object getJob() {
        if (downMap.isEmpty()) {
            return new Object();
        }
        Integer id = downMap.remove(0);
        finishMap.add(id);
        return Maps.of("job", "", "id", id);
    }

    @Override
    public void finishJob(JSONObject jobInfo) {
        Integer id = jobInfo.getInteger("id");
        finishMap.remove(id);
    }

    @Override
    public Object executeJob(JSONObject json) {
        Integer id = json.getInteger("id");
        CustomerPO customerPO = movieMapper.queryUserById(id);
        customerPO.setVipCreateTime(null);
        customerPO.setIsVip(0);
        customerPO.setVipMouth(0);
        movieMapper.insertUpdate(customerPO);
        return o;
    }
}
