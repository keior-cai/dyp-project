package com.sise.ccj.task.down;

import com.github.pagehelper.Page;
import com.sise.ccj.mapper.DypDbMapper;
import com.sise.ccj.mapper.PSpaceMapper;
import com.sise.ccj.pojo.common.PSpacePO;
import com.sise.ccj.request.admin.PSpaceRequest;
import com.sise.ccj.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @ClassName PSpaceDown
 * @Description
 * @Author CCJ
 * @Date 2020/2/1 1:04
 **/
@Component
public class PSpaceDown {


    @Autowired
    private PSpaceMapper pSpaceMapper;

    @Autowired
    private DypDbMapper dypDbMapper;

    @Scheduled(fixedRateString = "${time.down}")
    public void exectu(){
        List<String> dbName = dypDbMapper.queryDb();
        for (String db : dbName){
            if (db.equals("dyp_business"))continue;
            PSpaceRequest pSpaceRequest = new PSpaceRequest();
            pSpaceRequest.setStatus(0);
            pSpaceRequest.setDbPrefix(db);
            Page<PSpacePO> pSpacePOS = pSpaceMapper.queryPSpace(pSpaceRequest);
            for (PSpacePO pSpacePO : pSpacePOS.getResult()){
                String[] times = pSpacePO.getUpTime().split(":");
                Date time = new Date(1970,01,01,
                        Integer.parseInt(times[0]), Integer.parseInt(times[1]),
                        Integer.parseInt(times[2]));
                Date now = new Date();
                String[] nowTimes = DateHelper.toHH__mm__ss(now).split(":");
                Date nowTime = new Date(1970,01,01,
                        Integer.parseInt(nowTimes[0]), Integer.parseInt(nowTimes[1]),
                        Integer.parseInt(nowTimes[2]));
                if (time.getTime() >= nowTime.getTime()){
                    pSpacePO.setStatus(1);
                    pSpacePO.setDbPrefix(db);
                    pSpaceMapper.insertUpdate(pSpacePO);
                }
            }
        }
    }
}
