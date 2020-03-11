package com.sise.ccj.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TaskQuartzManager {
    private static final String JOB_GROUP_NAME = "JOB_GROUP_NAME";
    private static final String TRIGGER_GROUP_NAME = "TRIGGER_GROUP_NAME";
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    public static void addJob(String jobName, String triggerName,
                              Class<? extends Job> jobClass, String cron) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            // 任务名，任务组，任务执行类
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, JOB_GROUP_NAME).build();
            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, TRIGGER_GROUP_NAME);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            // 调度容器设置JobDetail和Trigger
            sched.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteJob(String jobName, String triggerName) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);
            sched.pauseTrigger(triggerKey);// 停止触发器
            sched.unscheduleJob(triggerKey);// 移除触发器
            sched.deleteJob(JobKey.jobKey(jobName, JOB_GROUP_NAME));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void pauseJob(String jobName) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            sched.pauseJob(JobKey.jobKey(jobName, JOB_GROUP_NAME));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void resumeJob(String jobName) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            sched.resumeJob(JobKey.jobKey(jobName, JOB_GROUP_NAME));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void startJobs() {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void pauseJobs() {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            sched.pauseAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void resumeJobs() {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            sched.resumeAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void shutdownJobs() {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:关闭所有定时任务
     */
    public static void shutdownJobs(boolean waitForJobsToComplete) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown(waitForJobsToComplete);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
