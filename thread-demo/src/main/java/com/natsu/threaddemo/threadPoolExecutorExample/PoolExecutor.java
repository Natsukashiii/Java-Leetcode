package com.natsu.threaddemo.threadPoolExecutorExample;

import java.util.concurrent.ExecutorService;

public class PoolExecutor {

  public static void main(String[] args) {
    ThreadPoolExecutorExample exec = new ThreadPoolExecutorExample();
    exec.init();
    ExecutorService pool = exec.getPollExecutor();
    for (int i = 0; i < 100; i++) {
      System.out.println("提交第" + i + "个任务!");
      pool.execute(new Runnable() {
        @Override
        public void run() {
          try {
            Thread.sleep(3000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("running=====");
        }
      });
    }

    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
