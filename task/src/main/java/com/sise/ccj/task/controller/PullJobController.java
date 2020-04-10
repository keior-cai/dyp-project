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
    public HttpBody getTimeJob() {
        return HttpBody.getSucInstance(jobMap.get("TimeOutJob").getJob());
    }

    @PostMapping("/finishTimeOutJob")
    public HttpBody finishTimeOutJob(@RequestBody JSONObject json) {
        jobMap.get("TimeOutJob").finishJob(json);
        return HttpBody.SUCCESS;
    }

    @GetMapping("/getSendJob")
    public HttpBody getSendJob() {
        return HttpBody.getSucInstance(jobMap.get("SendJob").getJob());
    }

    @PostMapping("/finishSendJob")
    public HttpBody finishSendJob(@RequestBody JSONObject json) {
        jobMap.get("SendJob").finishJob(json);
        return HttpBody.SUCCESS;
    }

    @GetMapping("/getMovieDownJob")
    public HttpBody getMovieDownJob() {
        return HttpBody.getSucInstance(jobMap.get("MovieDownJob").getJob());
    }

    @PostMapping("/finishMovieDownJob")
    public HttpBody finishMovieDownJob(@RequestBody JSONObject json) {
        jobMap.get("MovieDownJob").finishJob(json);
        return HttpBody.SUCCESS;
    }

    @GetMapping("/getPSpaceDownJob")
    public HttpBody getPSpaceDownJob() {
        return HttpBody.getSucInstance(jobMap.get("PSpaceDownJob").getJob());
    }

    @PostMapping("/finishPSpaceDownJob")
    public HttpBody finishPSpaceDownJob(@RequestBody JSONObject json) {
        jobMap.get("PSpaceDownJob").finishJob(json);
        return HttpBody.SUCCESS;
    }

    @GetMapping("/getVipDownJob")
    public HttpBody getVipDownJob() {
        return HttpBody.getSucInstance(jobMap.get("VipDownJob").getJob());
    }

    @PostMapping("/finishVipJob")
    public HttpBody finishVipJob(@RequestBody JSONObject json) {
        jobMap.get("VipDownJob").finishJob(json);
        return HttpBody.SUCCESS;
    }


    @GetMapping("/ping")
    public HttpBody ping() {
        return HttpBody.SUCCESS;
    }

    @PostMapping("/upSlave")
    public HttpBody upSlave(@RequestBody JSONObject json){
        return HttpBody.SUCCESS;
    }

}
