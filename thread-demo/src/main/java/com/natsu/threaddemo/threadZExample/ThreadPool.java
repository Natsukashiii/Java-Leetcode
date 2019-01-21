package com.natsu.threaddemo.threadZExample;

import java.util.LinkedList;

public class ThreadPool {

  //线程池大小
  int threadPoolSize;

  //任务容器
  LinkedList<Runnable> tasks = new LinkedList<>();

  //试图消费任务的线程
  public ThreadPool() {
    threadPoolSize = 10;

    //启动10个消费线程
    synchronized (tasks) {
      for (int i = 0; i < threadPoolSize; i++) {
        new TaskConsumeThread("Custom thread: " + i).start();
      }
    }
  }

  public void add(Runnable runnable){
    synchronized (tasks){
      tasks.add(runnable);
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
      System.out.println("run: " + this.getName());
      while (true) {
        synchronized (tasks) {
          while (tasks.isEmpty()) {
            try {
              tasks.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          task = tasks.removeLast();
          // 允许添加任务的线程可以继续添加 任务
          tasks.notifyAll();
        }
        System.out.println(this.getName() + " get task, is running");
        task.run();
      }
    }
  }

}
