package com.natsu.threaddemo.threadMethod;

public class method_sleep {

  public static void main(String[] args) {
    method_sleep();
  }

  /**
   * 当前线程暂停, 让出CPU使用权
   */
  public static void method_sleep() {
    System.out.println("===== method_sleep start =====");

    Thread thread = new Thread() {
      @Override
      public void run() {
        int seconds = 0;
        while (true) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.printf("playing %d s%n", seconds++);
        }
      }
    };
    thread.start();
  }
}
