package com.natsu.threaddemo.threadPool;

import java.util.LinkedList;

/**
 * 自定义线程池
 */
public class ThreadPool {

  // 线程池大小
  int threadPoolSize;

  // 任务容器
  LinkedList<Runnable> tasks = new LinkedList<Runnable>();

  // 试图消费任务的线程
  public ThreadPool(int threadPoolSize) {
    this.threadPoolSize = threadPoolSize;
    // 启动任务消费线程
    synchronized (tasks) {
      for (int i = 0; i < threadPoolSize; i++) {
        new TaskConsumeThread("任务线程 " + i).start();
      }
    }
  }

  public void add(Runnable runnable) {
    synchronized (tasks) {
      tasks.add(runnable);
      // 唤醒等待的任务消费线程
      tasks.notifyAll();
    }
  }

  class TaskConsumeThread extends Thread {

    public TaskConsumeThread(String name) {
      super(name);
    }
    Runnable task;

    @Override
    public void run() {
      System.out.println("running " + this.getName());
      while (true){
        synchronized (tasks) {
          while (tasks.isEmpty()) {
            try {
              tasks.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          task = tasks.removeLast();
          // 允许添加任务的线程可以继续添加任务
          tasks.notifyAll();
        }
        System.out.println(this.getName() + " get mission and execute.");
        task.run();
      }
    }
  }

}
