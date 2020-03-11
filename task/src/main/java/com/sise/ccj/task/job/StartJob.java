package com.sise.ccj.task.job;

import com.sise.ccj.mapper.DypDbMapper;
import com.sise.ccj.task.cluster.master.MasterCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Component
public class StartJob {


    @Autowired
    private DypDbMapper dypDbMapper;


    @Scheduled(fixedRate = 2000)
    public void startJob() {
        for (String db : getDb()) {
            if (db.equals("dyp_business")) {
                continue;
            }
            MasterCache.dbList.add(db);
        }
    }

//    @Scheduled(fixedRate = 13000)
//    public void startJob2(){
//        for (String db : getDb()) {
//            if (db.equals("dyp_business")){
//                continue;
//            }
//            for (Job2 job : jobMap2.values()){
//                job.execute(db);
//            }
//        }
//    }

    private List<String> getDb() {
        List<String> dbName = dypDbMapper.queryDb();
        if (CollectionUtils.isEmpty(dbName)) {
            return Collections.emptyList();
        }
        return dbName;
    }

}
