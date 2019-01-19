package com.natsu.quartzdemo;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.JobBuilder.newJob;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class TestQuartz {

  public static void main(String[] args) throws Exception {
    // 创建调度器
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

    // 定义一个触发器
    // 定义名称和所属的组
    Trigger trigger = newTrigger().withIdentity("tragger1", "group1")
        .startNow()
        .withSchedule(simpleSchedule()
            // 每隔2s执行一次
            .withIntervalInSeconds(2)
            // 总共执行11次(第一次执行不计数)
            .withRepeatCount(10))
        .build();

    // 定义一个Job detai
    JobDetail jobDetail = newJob(MailJob.class)
        .withIdentity("mailJob1", "mailgroup")
        .usingJobData("email", "xx@163.com")
        .build();

    // 将调度加入这个job
    scheduler.scheduleJob(jobDetail, trigger);

    // 启动job
    scheduler.start();

    // 等待20s,使得之前任务执行完之后,再关闭调度器
    Thread.sleep(20000);

    scheduler.shutdown();
  }
}
