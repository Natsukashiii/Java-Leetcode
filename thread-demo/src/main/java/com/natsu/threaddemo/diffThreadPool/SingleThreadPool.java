package com.natsu.threaddemo.diffThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程化的线程池,只用唯一的工作线程来执行任务保证所有任务按照指定顺序执行
 */
public class SingleThreadPool {

  public static void main(String[] args) {
    test1();
  }

  public static void test1() {
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 10; i++) {
      final int index = i;
      singleThreadExecutor.execute(new Runnable() {

        @Override
        public void run() {
          try {
            System.out.println(index);
            Thread.sleep(2000);
          } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      });
    }
  }
}
