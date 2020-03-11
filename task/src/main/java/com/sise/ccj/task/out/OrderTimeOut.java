package com.sise.ccj.task.out;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.StringUtil;
import com.sise.ccj.mapper.OpenOrderMapper;
import com.sise.ccj.mapper.OrderMapper;
import com.sise.ccj.mapper.PSpaceMapper;
import com.sise.ccj.pojo.common.OpenOrderPO;
import com.sise.ccj.pojo.common.OrderPO;
import com.sise.ccj.pojo.common.PSpacePO;
import com.sise.ccj.request.OrderRequest;
import com.sise.ccj.request.admin.PSpaceRequest;
import com.sise.ccj.task.cluster.master.MasterCache;
import com.sise.ccj.task.config.TaskConfig;
import com.sise.ccj.task.job.Job;
import com.sise.ccj.utils.DateHelper;
import com.sise.ccj.utils.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName OrderTimeOut
 * @Description
 * @Author CCJ
 * @Date 2020/2/6 20:53
 **/
@Slf4j
@Service("TimeOutJob")
public class OrderTimeOut implements Job {

    private static final Map<String, List<String>> timeOutMap = new ConcurrentHashMap<>();

    private static final Map<String, List<String>> finishMap = new ConcurrentHashMap<>();

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PSpaceMapper pSpaceMapper;

    @Autowired
    private OpenOrderMapper openOrderMapper;

    private static Object object = new Object();

    @Autowired
    private TaskConfig taskConfig;


    @Override
    public void execute(JobExecutionContext var1) {
        if (!timeOutMap.isEmpty()){
            return;
        }
        for (String db : MasterCache.dbList){
            OrderRequest request = new OrderRequest();
            request.setDbPrefix(db);
            request.setStatus(0);
            Date date = new Date();
            request.setEndTime(DateHelper.toYYYY_MM_DD_HH_MM_SS(DateUtils.addMilliseconds(date, taskConfig.getOrderTomeOut())));
            Page<OrderPO> baseVOList = orderMapper.queryOrder(request);
            if (CollectionUtils.isEmpty(baseVOList.getResult())) {
                return;
            }
            baseVOList.getResult().forEach(e-> {
                if (!timeOutMap.containsKey(db)){
                    timeOutMap.put(db, new ArrayList<>());
                    finishMap.put(db, new ArrayList<>());
                }
                timeOutMap.get(db).add(e.getOrderSn());
            });
        }
    }

    @Override
    public Object getJob() {
        if (timeOutMap.isEmpty()){
            return new Object();
        }
        String job = null,id = null;
        for (Map.Entry<String, List<String>> entry : timeOutMap.entrySet()) {
            if (CollectionUtils.isEmpty(entry.getValue())){
                continue;
            }
            job = entry.getKey();
            id = entry.getValue().remove(0);
        }
        if (!StringUtils.isEmpty(job) && !StringUtils.isEmpty(id)){
            finishMap.get(job).add(id);
            return Maps.of("job",job,"id",id);
        }
        return object;
    }

    @Override
    public void finishJob(JSONObject jobInfo) {
        String key = jobInfo.getString("job");
        String id = jobInfo.getString("id");
        List<String> jobList = finishMap.get(key);
        if (jobInfo.containsKey(id)){
            jobList.remove(key);
        }
    }

    @Override
    public Object executeJob(JSONObject json) {
        String db = json.getString("job");
        String id = json.getString("id");
        OrderPO orderPO = orderMapper.queryOrderByOrderSn(db, id);
        PSpaceRequest pSpaceRequest = new PSpaceRequest();
        pSpaceRequest.setDbPrefix(db);
        pSpaceRequest.setId(orderPO.getPsId());
        Page<PSpacePO> baseVO = pSpaceMapper.queryPSpace(pSpaceRequest);
        PSpacePO spacePO = baseVO.getResult().get(0);
        JSONArray array = JSON.parseArray(spacePO.getInfo());
        OpenOrderPO openOrderPO = new OpenOrderPO();
        openOrderPO.setType(1);
        openOrderPO.setDbPrefix(db);
        openOrderPO.setStatus(0);
        openOrderPO.setInfo(JSON.toJSONString(orderPO));
        JSONArray oldArray = JSON.parseArray(orderPO.getLocation());
        for (int i = 0; i < oldArray.size(); i++) {
            JSONObject location = oldArray.getJSONObject(i);
            int x = location.getIntValue("x");
            int y = location.getIntValue("y");
            array.getJSONArray(x).set(y, 0);
            spacePO.setInfo(JSON.toJSONString(array));
        }
        spacePO.setDbPrefix(db);
        spacePO.setNum(spacePO.getNum()+orderPO.getNum());
        pSpaceMapper.insertUpdate(spacePO);
        log.info("spacePO = {}", spacePO);
        orderPO.setStatus(5);
        orderPO.setDbPrefix(db);
        log.info("orderPO = {}", orderPO);
        orderMapper.insertUpdate(orderPO);
        // 写入回调
        openOrderMapper.insertUpdate(openOrderPO);
        return object;
    }
}
