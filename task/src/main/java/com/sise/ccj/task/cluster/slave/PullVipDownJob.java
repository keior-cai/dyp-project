package com.sise.ccj.task.cluster.slave;

import com.sise.ccj.compant.SpringContext;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component("PullVipDownJob")
public class PullVipDownJob implements Job {

    private static final String  getJobUri  = "/task/job/getVipDownJob";

    private static final String finishJobUri = "/task/job/finishVipDownJob";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        com.sise.ccj.task.job.Job job = (com.sise.ccj.task.job.Job) SpringContext.getBeanById("VipDownJob");
        PullHelp.Pull(getJobUri, finishJobUri, job);
    }
}
