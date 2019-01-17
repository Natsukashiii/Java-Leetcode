package com.natsu.threaddemo.diffThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可缓存线程池,如果线程池长度超过处理需要,可以灵活回收线程,若无可回收,则新建线程
 */
public class CachedThreadPool {

  public static void main(String[] args) {
    test1();
  }

  public static void test1() {
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      final int index = i;
      try {
        Thread.sleep(index * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      cachedThreadPool.execute(new Runnable() {

        @Override
        public void run() {
          System.out.println(index);
        }
      });
    }
  }

}
