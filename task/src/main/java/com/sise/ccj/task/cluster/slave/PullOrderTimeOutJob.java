package com.sise.ccj.task.cluster.slave;

import com.sise.ccj.compant.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

@Slf4j
@Service("/PullOrderTimeOutJob")
@DisallowConcurrentExecution
public class PullOrderTimeOutJob implements Job {

    private String getJobUri = "/task/job/getTimeOutJob";

    private String finishJobUri = "/task/job/finishTimeOutJob";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        com.sise.ccj.task.job.Job job = (com.sise.ccj.task.job.Job) SpringContext.getBeanById("TimeOutJob");
        PullHelp.Pull(getJobUri, finishJobUri, job);
    }
}
