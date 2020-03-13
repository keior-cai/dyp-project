package com.sise.ccj.task.down;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.sise.ccj.compant.SpringContext;
import com.sise.ccj.mapper.PSpaceMapper;
import com.sise.ccj.pojo.common.PSpacePO;
import com.sise.ccj.request.admin.PSpaceRequest;
import com.sise.ccj.task.cluster.master.MasterCache;
import com.sise.ccj.task.job.Job;
import com.sise.ccj.utils.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;

/**
 * @ClassName PSpaceDown
 * @Description
 * @Author CCJ
 * @Date 2020/2/1 1:04
 **/
@Slf4j
@Service("PSpaceDownJob")
@DisallowConcurrentExecution
public class PSpaceDown implements Job {

    @Override
    public void execute(JobExecutionContext var1) {
        PSpaceMapper pSpaceMapper = SpringContext.getBeanByType(PSpaceMapper.class);
        for (String db : MasterCache.dbList) {
            PSpaceRequest pSpaceRequest = new PSpaceRequest();
            pSpaceRequest.setStatus(0);
            pSpaceRequest.setDbPrefix(db);
            Page<PSpacePO> pSpacePOS = pSpaceMapper.queryPSpace(pSpaceRequest);
            if (CollectionUtils.isEmpty(pSpacePOS.getResult())) {
                return;
            }
            for (PSpacePO pSpacePO : pSpacePOS.getResult()) {
                String[] times = pSpacePO.getUpTime().split(":");
                Date time = new Date(1970, 01, 01,
                        Integer.parseInt(times[0]), Integer.parseInt(times[1]),
                        Integer.parseInt(times[2]));
                Date now = new Date();
                String[] nowTimes = DateHelper.toHH__mm__ss(now).split(":");
                Date nowTime = new Date(1970, 01, 01,
                        Integer.parseInt(nowTimes[0]), Integer.parseInt(nowTimes[1]),
                        Integer.parseInt(nowTimes[2]));
                if (time.getTime() >= nowTime.getTime()) {
                    pSpacePO.setStatus(1);
                    pSpacePO.setDbPrefix(db);
                    pSpaceMapper.insertUpdate(pSpacePO);
                }
            }
        }
    }

    @Override
    public Object getJob() {
        return null;
    }

    @Override
    public void finishJob(JSONObject jobInfo) {

    }

    @Override
    public Object executeJob(JSONObject json) {
        return null;
    }
}
