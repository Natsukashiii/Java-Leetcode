package com.natsu.threaddemo.threadPoolExecutorExample;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorExample {

  private ThreadPoolExecutor pool = null;

  /**
   * 线程池初始化方法
   *
   * corePoolSize 核心线程池大小----10 maximumPoolSize 最大线程池大小----30 keepAliveTime
   * 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
   * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(10)====10容量的阻塞队列 threadFactory 新建线程工厂----new
   * CustomThreadFactory()====定制的线程工厂 rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时,
   * 即当提交第41个任务时(前面线程都没有执行完,此测试方法中用sleep(100)), 任务会交给RejectedExecutionHandler来处理
   */
  public void init() {
    pool = new ThreadPoolExecutor(10, 30, 30, TimeUnit.MINUTES,
        new ArrayBlockingQueue<Runnable>(10), new CustomThreadFactory(),
        new CustomRejectedExecutionHandler());
  }

  public void destory() {
    if (pool != null) {
      pool.shutdownNow();
    }
  }

  public ExecutorService getPollExecutor() {
    return this.pool;
  }

  private class CustomThreadFactory implements ThreadFactory {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable runnable) {
      Thread thread = new Thread(runnable);
      String threadName = CustomThreadFactory.class.getSimpleName() + count.addAndGet(1);
      thread.setName(threadName);
      return thread;
    }
  }

  private class CustomRejectedExecutionHandler implements RejectedExecutionHandler{
    @Override
    public void rejectedExecution(Runnable runnable,ThreadPoolExecutor executor){
      System.out.println("error ......");
    }
  }

}
