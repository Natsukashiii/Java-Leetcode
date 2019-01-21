package com.natsu.threaddemo.threadZExample.diffthreadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定长线程池,支持定时及周期性任务执行
 */
public class ScheduledThreadPool {

  public static void main(String[] args) {
//    delay();
    fix();
  }

  /**
   * 延迟3秒执行
   */
  public static void delay() {
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    scheduledThreadPool.schedule(new Runnable() {

      @Override
      public void run() {
        System.out.println("delay 3 seconds");
      }
    }, 3, TimeUnit.SECONDS);
  }

  /**
   * 延迟1秒后每3秒执行一次
   */
  public static void fix() {
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

      @Override
      public void run() {
        System.out.println("delay 1 seconds, and excute every 3 seconds");
      }
    }, 1, 3, TimeUnit.SECONDS);
  }
}
