package com.sise.ccj.task.cluster.slave;

import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.compant.SpringContext;
import com.sise.ccj.task.cluster.master.MasterCache;
import com.sise.ccj.task.config.TaskConfig;
import com.sise.ccj.task.init.InitTask;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service("/PullOrderTimeOutJob")
@DisallowConcurrentExecution
public class PullOrderTimeOutJob implements Job {

    @Autowired
    @Qualifier("TimeOutJob")
    private com.sise.ccj.task.job.Job job;

    private String getJobUri = "/task/job/getTimeOutJob";

    private String finishJobUri = "/task/job/finishTimeOutJob";

    @Autowired
    private TaskConfig taskConfig;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (MasterCache.isMaster){
            // master 节点不干活
            return;
        }
        RestTemplate client = new RestTemplate();
        boolean hasExecption = true;
        for (int i = 0; i < MasterCache.tryCount; i++) {
            try {
                ResponseEntity<JSONObject> response = client.exchange(MasterCache.masterEnv + getJobUri, HttpMethod.GET, null, JSONObject.class);
                JSONObject json = response.getBody();
                if (json == null || json.getJSONObject("data") == null) {
                    return;
                }
                JSONObject data = json.getJSONObject("data");
                if (data.isEmpty()) {
                    return;
                }
                job.executeJob(data);
                // 通知master
                client.exchange(MasterCache.masterEnv + finishJobUri, HttpMethod.POST, new HttpEntity<>(data), String.class);
                hasExecption = false;
                break;
            } catch (Exception e) {
                log.info("{}", e);
            }
        }
        if (hasExecption) {
            InitTask init = SpringContext.getBeanByType(InitTask.class);
            init.run();
        }
    }
}
