package com.sise.ccj.task.cluster.slave;

import com.sise.ccj.compant.SpringContext;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component("PullMovieDownJob")
public class PullMovieDownJob implements Job {

    private static final String  getJobUri  = "/task/job/getMovieDownJob";

    private static final String finishJobUri = "/task/job/finishMovieDownJob";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        com.sise.ccj.task.job.Job job = (com.sise.ccj.task.job.Job) SpringContext.getBeanById("MovieDownJob");
        PullHelp.Pull(getJobUri, finishJobUri, job);
    }
}
