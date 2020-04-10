package com.sise.ccj.task.cluster.slave;

import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.compant.SpringContext;
import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.task.cluster.master.MasterCache;
import com.sise.ccj.task.config.TaskConfig;
import com.sise.ccj.task.constant.TaskConstant;
import com.sise.ccj.task.init.InitTask;
import com.sise.ccj.task.job.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
public class PullHelp {
    private PullHelp(){}
    private static RedisUtil redisUtil;
    private static TaskConfig taskConfig;
    public static final String key = "task:slave:key";
    public static void Pull(String jobUri, String finishJobUri, Job job){
        if (redisUtil == null || taskConfig == null){
            redisUtil = SpringContext.getBeanByType(RedisUtil.class);
            taskConfig = (TaskConfig) SpringContext.getBeanById("taskConfig");
        }
        String masterEnv = redisUtil.get(TaskConstant.MASTER_ENV)+"";
        if (MasterCache.isMaster && taskConfig.getEnv().equals(masterEnv)){
            if (redisUtil.entries(key).size() > 1) {
                // master 节点不干活
                return;
            }
        }
        RestTemplate client = new RestTemplate();
        boolean hasExecption = true;
        for (int i = 0; i < MasterCache.tryCount; i++) {
            try {
                ResponseEntity<JSONObject> response = client.exchange(MasterCache.masterEnv + jobUri, HttpMethod.GET, null, JSONObject.class);
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
                log.info("slave 消费任务{},id={}", job.getClass(), data);
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
