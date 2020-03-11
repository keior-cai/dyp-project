package com.sise.ccj.task.cluster.slave;

import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.task.Init;
import com.sise.ccj.task.cluster.master.MasterCache;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service("/PullOrderTimeOutJob")
public class PullOrderTimeOutJob implements Job {

    @Autowired
    private Init init;

    @Autowired
    @Qualifier("TimeOutJob")
    private com.sise.ccj.task.job.Job job;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        RestTemplate client = new RestTemplate();
        boolean hasExecption = true;
        for (int i = 0; i < MasterCache.tryCount; i++) {
            try {
                ResponseEntity<JSONObject> response = client.exchange(MasterCache.masterEnv, HttpMethod.GET, null, JSONObject.class);
                job.executeJob(response.getBody());
                // 通知master
                hasExecption = false;
                break;
            } catch (Exception e) {
                log.info("{}", e);
            }
        }
        if (hasExecption) {
            init.selectorMaster();
        }
    }
}
