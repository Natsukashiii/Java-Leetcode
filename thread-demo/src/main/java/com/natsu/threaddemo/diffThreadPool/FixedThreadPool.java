package com.natsu.threaddemo.diffThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 定长线程池,控制线程最大并发数,超出的线程在队列中等待
 */
public class FixedThreadPool {

  public static void main(String[] args) {
    test1();
  }

  public static void test1() {
    // 线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 10; i++) {
      final int index = i;
      fixedThreadPool.execute(new Runnable() {

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
