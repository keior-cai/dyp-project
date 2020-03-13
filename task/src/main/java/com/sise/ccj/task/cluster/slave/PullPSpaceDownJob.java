package com.sise.ccj.task.cluster.slave;

import com.sise.ccj.compant.SpringContext;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component("PullPSpaceDownJob")
public class PullPSpaceDownJob implements Job {

    private static final String getJobUri  = "/task/job/getPSpaceDownJob";

    private static final String  finishJobUri = "/task/job/finishPSpaceDownJob";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        com.sise.ccj.task.job.Job job = (com.sise.ccj.task.job.Job) SpringContext.getBeanById("PSpaceDownJob");
        PullHelp.Pull(getJobUri, finishJobUri, job);
    }
}
