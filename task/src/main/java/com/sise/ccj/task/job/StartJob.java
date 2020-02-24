package com.sise.ccj.task.job;

import com.github.pagehelper.Page;
import com.sise.ccj.mapper.DypDbMapper;
import com.sise.ccj.pojo.common.MoviePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Component
public class StartJob {


    @Autowired
    private DypDbMapper dypDbMapper;

    @Autowired
    private Map<String, Job> jobMap;


    @Scheduled(fixedRate = 2000)
    public void startJob(){
        List<String> dbName = dypDbMapper.queryDb();
        if (CollectionUtils.isEmpty(dbName)){
            return;
        }
        for (String db : dbName) {
            if (db.equals("dyp_business")){
                continue;
            }
            for (Job job : jobMap.values()){
                job.execute(db);
            }
        }
    }
}
