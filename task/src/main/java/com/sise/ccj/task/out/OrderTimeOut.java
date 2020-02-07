package com.sise.ccj.task.out;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.StringUtil;
import com.sise.ccj.mapper.DypDbMapper;
import com.sise.ccj.mapper.OpenOrderMapper;
import com.sise.ccj.mapper.OrderMapper;
import com.sise.ccj.mapper.PSpaceMapper;
import com.sise.ccj.pojo.common.OpenOrderPO;
import com.sise.ccj.pojo.common.OrderPO;
import com.sise.ccj.pojo.common.PSpacePO;
import com.sise.ccj.request.OrderRequest;
import com.sise.ccj.request.admin.PSpaceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName OrderTimeOut
 * @Description
 * @Author CCJ
 * @Date 2020/2/6 20:53
 **/
@Slf4j
@Service
public class OrderTimeOut {


    @Value("${time.sec}")
    private Long orderTimeOut;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PSpaceMapper pSpaceMapper;

    @Autowired
    private OpenOrderMapper openOrderMapper;

    private String orderTimeKey = "dyp:time:order:start";

    @Autowired
    private DypDbMapper dypDbMapper;

    @Scheduled(fixedRateString = "${time.out}")
    public void timeOutOrderSchedu() {
        OrderRequest request = new OrderRequest();
        request.setStatus(0);
        List<String> dbName = dypDbMapper.queryDb();
        if (CollectionUtils.isEmpty(dbName)) return;
        for (String str : dbName) {
            if (str.equals("dyp_business")) continue;
            request.setDbPrefix(str);
            Page<OrderPO> baseVOList = orderMapper.queryOrder(request);
            if (CollectionUtils.isEmpty(baseVOList.getResult())) continue;
            for (OrderPO orderPO : baseVOList.getResult()) {
                if (System.currentTimeMillis() - orderPO.getCreateTime().getTime()
                        >= orderTimeOut) {
                    // 归还资源
                    PSpaceRequest pSpaceRequest = new PSpaceRequest();
                    pSpaceRequest.setDbPrefix(str);
                    pSpaceRequest.setId(orderPO.getPsId());
                    Page<PSpacePO> baseVO = pSpaceMapper.queryPSpace(pSpaceRequest);
                    PSpacePO spacePO = baseVO.getResult().get(0);
                    JSONArray array = JSON.parseArray(spacePO.getInfo());
                    if (StringUtil.isEmpty(orderPO.getLocation())){
                        continue;
                    }
                    OpenOrderPO openOrderPO = new OpenOrderPO();
                    openOrderPO.setType(1);
                    openOrderPO.setDbPrefix(str);
                    openOrderPO.setStatus(0);
                    openOrderPO.setInfo(JSON.toJSONString(orderPO));
                    JSONArray oldArray = JSON.parseArray(orderPO.getLocation());
                    for (int i = 0; i < oldArray.size(); i++) {
                        JSONObject json = oldArray.getJSONObject(i);
                        int x = json.getIntValue("x");
                        int y = json.getIntValue("y");
                        array.getJSONArray(x).set(y, 0);
                        spacePO.setInfo(JSON.toJSONString(array));
                    }
                    spacePO.setDbPrefix(str);
                    spacePO.setNum(spacePO.getNum()+orderPO.getNum());
                    pSpaceMapper.insertUpdate(spacePO);
                    log.info("spacePO = {}", spacePO);
                    orderPO.setStatus(5);
                    orderPO.setDbPrefix(str);
                    log.info("orderPO = {}", orderPO);
                    orderMapper.insertUpdate(orderPO);
                    // 写入回调
                    openOrderMapper.insertUpdate(openOrderPO);
                }
            }
        }
    }
}
