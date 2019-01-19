package com.natsu.quartzdemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

public class MailJob implements Job {
  public void execute(JobExecutionContext context){
    JobDetail jobDetail = context.getJobDetail();
    String email = jobDetail.getJobDataMap().getString("email");

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    String now = dateFormat.format(new Date());

    System.out.printf("sending email to %s, time is %s%n", email, now);
  }

}
