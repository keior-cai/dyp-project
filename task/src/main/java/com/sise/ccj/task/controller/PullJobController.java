package com.sise.ccj.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.task.job.Job;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/job")
public class PullJobController {

    @Autowired
    private Map<String, Job> jobMap;


    @GetMapping("/getTimeOutJob")
    public HttpBody getTimeJob(){
        return HttpBody.getSucInstance(jobMap.get("timeOutJob").getJob());
    }

    @PostMapping("/finishTimeOutJob")
    public HttpBody finishTimeOutJob(@RequestBody JSONObject json){
        jobMap.get("timeOutJob").finishJob(json);
        return HttpBody.SUCCESS;
    }

}
