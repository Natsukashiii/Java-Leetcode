package com.natsu.threaddemo.threadPool.ThreadPoolExecutorCreate;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorCreate {

  final static int corePoolSize = 1;
  final static int maximumPoolSize = 2;
  final static int keepAliveTime = 60;
  final static int QUEUE_SIZE = 4;

  public static void main(String[] args) {
    create_ThreadPoolExecutor();
  }

  /**
   * 创建了一个自定义线程池 corePoolSize核心线程1个: 没有任务需要执行的时候线程池的大小，如果任务数比较多会开启其他线程，但是不会超过最大线程数。
   * 在刚刚创建ThreadPoolExecutor的时候，线程并不会立即启动，而是要等到有任务提交时才会启动， 除非调用了prestartCoreThread/prestartAllCoreThreads事先启动核心线程
   * maximumPoolSize最大线程2个: 线程池中允许的最大线程数，线程池中的当前线程数目不会超过该值。如果任务比较多的时候，可以将任务添加到缓冲池中。
   * keepAliveTime存活时间: 120s 缓冲任务3: 超过(分配的任务数大于最大线程数和缓冲线程数之和)就会被reject拒绝
   */
  public static void create_ThreadPoolExecutor() {
    // (corePoolSize, maximumPoolSize, keepAliveTime, 时间单位, 缓冲池, 拒绝任务类)
    ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
        TimeUnit.SECONDS,
        new ArrayBlockingQueue<Runnable>(QUEUE_SIZE), new Reject());
    //在这里由于任务队列是4, 加上线程池内最大线程数, 会有4个任务被拒绝(由于没做任何处理, 直接被抛弃)
    //当任务队列改为8时, 所有任务都不会被拒绝

    Task task1 = new Task("1", "任务1");
    Task task2 = new Task("2", "任务2");
    Task task3 = new Task("3", "任务3");
    Task task4 = new Task("4", "任务4");
    Task task5 = new Task("5", "任务5");
    Task task6 = new Task("6", "任务6");
    Task task7 = new Task("7", "任务7");
    Task task8 = new Task("8", "任务8");
    Task task9 = new Task("9", "任务9");
    Task task10 = new Task("10", "任务10");

    pool.execute(task1);
    pool.execute(task2);
    pool.execute(task3);
    pool.execute(task4);
    pool.execute(task5);
    pool.execute(task6);
    pool.execute(task7);
    pool.execute(task8);
    pool.execute(task9);
    pool.execute(task10);
    getPoolData(pool);
  }

  /**
   * 获取线程池数据
   */
  public static void getPoolData(ThreadPoolExecutor pool) {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (true) {
          try {
            Thread.sleep(1000);
            System.out.println(" >>>>>>>> 返回核心线程数:" + pool.getCorePoolSize());
            System.out.println(" >>>>>>>> 返回正在执行任务的线程的数量:" + pool.getActiveCount());
            System.out.println(" >>>>>>>> 返回等待队列中任务的总数:" + pool.getQueue().size());
            System.out.println(" >>>>>>>> 返回池中当前的线程数:" + pool.getPoolSize());
            System.out.println(" >>>>>>>> 返回计划执行的任务的总数:" + pool.getTaskCount());
            System.out.println(" >>>>>>>> 返回完成执行的任务的总数:" + pool.getCompletedTaskCount());
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });
    thread.start();
  }

}
