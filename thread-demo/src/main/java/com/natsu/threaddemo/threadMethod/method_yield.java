package com.natsu.threaddemo.threadMethod;

import com.natsu.threaddemo.threadMethod.Character.Hero;

public class method_yield {

  final static Hero gareen = new Hero("gareen", 16, 1);
  final static Hero teemo = new Hero("teemo", 13, 1);
  final static Hero SA = new Hero("SA", 15, 1);
  final static Hero VS = new Hero("VS", 15, 1);

  public static void main(String[] args) {
    method_yield();
  }

  /**
   * 线程临时暂停
   */
  public static void method_yield() {
    System.out.println("===== method_yield start =====");

    Thread thread1 = new Thread() {
      @Override
      public void run() {
        while (!teemo.isDead()) {
          gareen.attackHero(teemo);
        }
      }
    };

    Thread thread2 = new Thread() {
      @Override
      public void run() {
        while (!VS.isDead()) {
          // 临时暂停,使得thread1可以占用CPU资源
          Thread.yield();
          SA.attackHero(VS);
        }
      }
    };

    thread1.setPriority(5);
    thread2.setPriority(5);

    thread1.start();
    thread2.start();
  }

  public static void method_yield_02() {
    System.out.println("===== method_yield_02 start =====");

    Thread thread1 = new Thread() {
      @Override
      public void run() {
        while (!teemo.isDead()) {
          gareen.attackHero(teemo);
          Thread.yield();
        }
      }
    };

    Thread thread2 = new Thread() {
      @Override
      public void run() {
        while (!VS.isDead()) {
          SA.attackHero(VS);
          // 临时暂停,使得thread1可以占用CPU资源
          Thread.yield();
        }
      }
    };

    thread1.setPriority(Thread.MIN_PRIORITY);
    thread2.setPriority(Thread.MAX_PRIORITY);

    thread1.start();
    thread2.start();
  }
}
