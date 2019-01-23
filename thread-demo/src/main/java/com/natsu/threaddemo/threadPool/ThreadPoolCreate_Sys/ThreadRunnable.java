package com.natsu.threaddemo.threadPool.ThreadPoolCreate_Sys;

public class ThreadRunnable implements Runnable {

  private String name;

  public ThreadRunnable(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    for (int i = 0; i < 5; i++) {
      System.out.println(Thread.currentThread().getName() + " " + this.name + " is running!");
    }
  }
}
