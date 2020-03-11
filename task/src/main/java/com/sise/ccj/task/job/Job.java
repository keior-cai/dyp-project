package com.sise.ccj.task.job;

import com.alibaba.fastjson.JSONObject;

public interface Job extends org.quartz.Job {
    Object getJob();

    void finishJob(JSONObject jobInfo);

    Object executeJob(JSONObject json);

}
