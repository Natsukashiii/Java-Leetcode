package com.natsu.threaddemo.threadConnect;

import com.natsu.threaddemo.threadConnect.Character.Hero;
import com.natsu.threaddemo.threadConnect.Character.Hero_Wait_Notify;

public class connect_example {

  public static void main(String[] args) {
//    connect_test();
    connect_wait_notify();
  }

  public static void connect_test() {
    System.out.println("===== connect_test start =====");
    final Hero gareen = new Hero("gareen", 10);
    Thread thread1 = new Thread() {
      @Override
      public void run() {
        while (true) {
          // while循环判断是不是1, 是的话就不停循环, 直到血量恢复
          while (gareen.hp == 1) {
            continue;
          }
          gareen.hurt();
          System.out.printf("thread1: %s is hurting 1hp, %s remain hp is %.0f%n", gareen.name,
              gareen.name, gareen.hp);
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };
    thread1.start();

    Thread thread2 = new Thread() {
      @Override
      public void run() {
        while (true) {
          gareen.recover();
          System.out.printf("thread2: %s is recover 1hp, %s remain hp is %.0f%n", gareen.name,
              gareen.name, gareen.hp);
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };
    thread2.start();
    System.out.println("connect_test end...");
  }

  /**
   * 使用wait/notify进行交互
   */
  public static void connect_wait_notify() {
    System.out.println("===== connect_wait_notify start =====");

    final Hero_Wait_Notify gareen = new Hero_Wait_Notify("gareen", 100);
    Thread thread1 = new Thread() {
      @Override
      public void run() {
        while (true) {
          gareen.hurt();
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };
    thread1.start();

    Thread thread2 = new Thread() {
      @Override
      public void run() {
        while (true) {
          gareen.recover();
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };
    thread2.start();
    System.out.println("connect_wait_notify end...");
  }

}
