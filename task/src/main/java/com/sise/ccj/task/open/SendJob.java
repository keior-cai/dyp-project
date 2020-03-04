package com.sise.ccj.task.open;

import com.sise.ccj.mapper.OpenOrderMapper;
import com.sise.ccj.pojo.common.OpenOrderPO;
import com.sise.ccj.task.job.Job2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class SendJob implements Job2 {

    @Autowired
    private OpenOrderMapper openOrderMapper;

    private RestTemplate client = new RestTemplate();

    @Override
    public void execute(String db) {
        List<OpenOrderPO> openOrderPOList = openOrderMapper.queryList(db, 0);
        if (CollectionUtils.isEmpty(openOrderPOList)){
            return;
        }
        for (OpenOrderPO openOrderPO: openOrderPOList){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> entity = new HttpEntity<>(openOrderPO.getInfo(), headers);
            ResponseEntity<String> response =  client.exchange(openOrderPO.getUrl(), HttpMethod.POST,entity , String.class);
            log.info("{}", response.getBody());
        }
    }
}
