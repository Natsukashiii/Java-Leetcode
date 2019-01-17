package com.natsu.threaddemo.threadCreate;

public class ThreadDemo extends Thread {

  @Override
  public void run() {
    super.run();
    System.out.println(currentThread().getName() + ": " + currentThread().getId());
  }
}
