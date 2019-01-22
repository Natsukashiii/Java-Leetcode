package com.natsu.threaddemo.threadMethod;

public class method_daemon {

  public static void main(String[] args) {
    thread_all_daemon();
    thread_not_all_daemon();
  }
  /**
   * 只有守护线程的进程
   */
  public static void thread_all_daemon() {
    System.out.println("===== thread_all_daemon start =====");

    Thread thread1 = new Thread() {
      int seconds = 0;

      @Override
      public void run() {
        while (true) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.printf("playing %d s %n", seconds++);
        }
      }
    };

    thread1.setDaemon(true);
    thread1.start();
  }

  public static void thread_not_all_daemon() {
    System.out.println("===== thread_not_all_daemon start =====");

    Thread thread1 = new Thread() {
      int seconds = 0;
      @Override
      public void run() {
        while (true) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.printf("thread1 playing %d s %n", seconds++);
        }
      }
    };

    Thread thread2 = new Thread() {
      int seconds = 0;
      @Override
      public void run() {
        while (true) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.printf("thread2 playing %d s %n", seconds++);
        }
      }
    };

    thread1.setDaemon(true);
    thread1.start();
    thread2.start();
  }
}
