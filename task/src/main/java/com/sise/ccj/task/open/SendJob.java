package com.sise.ccj.task.open;

import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.compant.SpringContext;
import com.sise.ccj.mapper.OpenOrderMapper;
import com.sise.ccj.pojo.common.OpenOrderPO;
import com.sise.ccj.task.cluster.master.MasterCache;
import com.sise.ccj.task.job.Job;
import com.sise.ccj.utils.Maps;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service("SendJob")
@DisallowConcurrentExecution
public class SendJob implements Job {

    @Autowired
    private OpenOrderMapper openOrderMapper;

    private RestTemplate client = new RestTemplate();

    private static final Map<String, List<String>> sendMap = new ConcurrentHashMap<>();

    private static final Map<String, List<String>> finishMap = new ConcurrentHashMap<>();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        OpenOrderMapper openOrderMapper = SpringContext.getBeanByType(OpenOrderMapper.class);
        for (String db : MasterCache.dbList) {
            if (!sendMap.containsKey(db)) {
                sendMap.put(db, new ArrayList<>());
                finishMap.put(db, new ArrayList<>());
            }
            List<OpenOrderPO> openOrderPOList = openOrderMapper.queryList(db, 0);
            if (CollectionUtils.isEmpty(openOrderPOList)) {
                continue;
            }
            for (OpenOrderPO openOrderPO : openOrderPOList) {
                if (StringUtils.isEmpty(openOrderPO.getUrl())) {
                    continue;
                }
                sendMap.get(db).add(openOrderPO.getId() + "");
            }
        }
    }

    @Override
    public Object getJob() {
        if (sendMap.isEmpty()) {
            return new Object();
        }
        String job = null, id = null;
        for (Map.Entry<String, List<String>> entry : sendMap.entrySet()) {
            if (CollectionUtils.isEmpty(entry.getValue())) {
                continue;
            }
            job = entry.getKey();
            id = entry.getValue().remove(0);
        }
        if (!StringUtils.isEmpty(job) && !StringUtils.isEmpty(id)) {
            finishMap.get(job).add(id);
            return Maps.of("job", job, "id", id);
        }
        return new Object();
    }

    @Override
    public void finishJob(JSONObject jobInfo) {
        String key = jobInfo.getString("job");
        String id = jobInfo.getString("id");
        List<String> jobList = finishMap.get(key);
        if (jobInfo.containsKey(id)) {
            jobList.remove(key);
        }
    }

    @Override
    public Object executeJob(JSONObject json) {
        String db = json.getString("job");
        Integer id = json.getIntValue("id");
        OpenOrderPO openOrderPO = openOrderMapper.queryById(db, id);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> entity = new HttpEntity<>(openOrderPO.getInfo(), headers);
            ResponseEntity<String> response = client.exchange(openOrderPO.getUrl(), HttpMethod.POST, entity, String.class);
            log.info("{}", response.getBody());
        } catch (Exception e) {
            log.error("{}, 回调失败", openOrderPO);
        }
        openOrderPO.setStatus(1);
        openOrderMapper.insertUpdate(openOrderPO);
        return new Object();
    }
}
