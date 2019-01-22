package com.natsu.threaddemo.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolCreate_Sys {

  public static void main(String[] args) {
//    create_SingleThreadExecutor();
    create_CacheThreadPool();
//    create_FixedThreadPool();
//    create_ScheduledThreadPool();
  }

  /**
   * 创建了只有一条线程的线程池, 执行线程时, 三个线程依次执行
   */
  public static void create_SingleThreadExecutor() {
    //通过Executors工具类 的newSingleThreadExecutor方法创建线程池
    ExecutorService executor = Executors.newSingleThreadExecutor();

    //创建线程对象
    Thread t1 = new Thread(new ThreadRunnable("A"));
    Thread t2 = new Thread(new ThreadRunnable("B"));
    Thread t3 = new Thread(new ThreadRunnable("C"));

    //使用线程池调度线程
    executor.execute(t1);
    executor.execute(t2);
    executor.execute(t3);

    //关闭线程池
    executor.shutdown();
  }

  /**
   * 创建了固定线程数的线程池, 下图示例为3 在使用的时候, 若需要的线程数不大于3时, 线程池会自分配
   */
  public static void create_FixedThreadPool() {
    //通过Executors工具类 的newFixedThreadPool方法创建线程池
    // 指定线程池线程数为3
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    //创建线程对象
    Thread thread1 = new Thread(new ThreadRunnable("A"));
    Thread thread2 = new Thread(new ThreadRunnable("B"));
    Thread thread3 = new Thread(new ThreadRunnable("C"));
    Thread thread4 = new Thread(new ThreadRunnable("D"));
    Thread thread5 = new Thread(new ThreadRunnable("E"));

    // 使用线程池调度线程
    executorService.execute(thread1);
    executorService.execute(thread2);
    executorService.execute(thread3);
    executorService.execute(thread4);
    executorService.execute(thread5);

    //关闭线程池
    executorService.shutdown();
  }

  /**
   * 创建了一个可缓存的线程池, 如果线程池的大小超过了任务处理所需要的线程 就会回收部分空闲(60s不执行任务的线程), 当任务数量增加时, 线程池又能智能添加新线程来处理任务
   * 次线程不会对线程池大小做限制, 线程池大小取决于操作系统或者说是JVM能创建的最大线程大小
   */
  public static void create_CacheThreadPool() {
    //通过Executors工具类 的newFixedThreadPool方法创建线程池
    ExecutorService executorService = Executors.newCachedThreadPool();

    //创建线程对象
    Thread thread1 = new Thread(new ThreadRunnable("A"));
    Thread thread2 = new Thread(new ThreadRunnable("B"));
    Thread thread3 = new Thread(new ThreadRunnable("C"));
    Thread thread4 = new Thread(new ThreadRunnable("D"));
    Thread thread5 = new Thread(new ThreadRunnable("E"));

    // 使用线程池调度线程
    executorService.execute(thread1);
    executorService.execute(thread2);
    executorService.execute(thread3);
    executorService.execute(thread4);
    executorService.execute(thread5);

    //关闭线程池
    executorService.shutdown();
  }

  /**
   * 创建了一个定时执行的线程池
   */
  public static void create_ScheduledThreadPool() {
    //指定线程池中的线程数。若线程数小于需要线程数，则需要等待前面线程执行完成后再执行
    ScheduledExecutorService e = new ScheduledThreadPoolExecutor(1) {
    };
    //构造参数为：（进程，初始时间，间隔时间，时间单位）
    e.scheduleAtFixedRate(new ThreadRunnable("A"), 1000, 1000,
        TimeUnit.MILLISECONDS);
    e.scheduleAtFixedRate(new ThreadRunnable("B"), 1000, 5000, TimeUnit.MILLISECONDS);
  }


}
