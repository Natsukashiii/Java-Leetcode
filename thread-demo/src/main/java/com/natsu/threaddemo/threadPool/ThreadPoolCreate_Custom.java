package com.natsu.threaddemo.threadPool;

public class ThreadPoolCreate_Custom {

  public static void main(String[] args) {
    threadpool_test_02();
  }

  /**
   * 一个简单的线程池
   */
  public static void threadpool_test_01() {
    ThreadPool pool = new ThreadPool(10);
    for (int i = 0; i < 5; i++) {
      Runnable task = new Runnable() {
        @Override
        public void run() {
          System.out.println("I'm doing!");
        }
      };
      pool.add(task);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 刚开始每隔一秒往线程池添加任务,之后间隔时间越来越短,执行任务的线程还没来得及结束,新的任务又来了 线程池里的其他线程被唤醒来执行这些任务
   */
  public static void threadpool_test_02() {
    ThreadPool pool = new ThreadPool(10);
    int sleep = 1000;
    while (true) {
      pool.add(new Runnable() {
        @Override
        public void run() {
          System.out.println("I'm doing!");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
      try {
        Thread.sleep(sleep);
        sleep = sleep > 100 ? sleep - 100 : sleep;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
