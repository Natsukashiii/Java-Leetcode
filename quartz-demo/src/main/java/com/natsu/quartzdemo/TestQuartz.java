package com.natsu.quartzdemo;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class TestQuartz {

  public static void main(String[] args) throws SchedulerException {
    // 创建调度器
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

    // 定义一个触发器
//    Trigger trigger=
  }
}
