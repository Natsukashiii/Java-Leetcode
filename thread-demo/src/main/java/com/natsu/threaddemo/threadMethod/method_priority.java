package com.natsu.threaddemo.threadMethod;

import com.natsu.threaddemo.threadMethod.Character.Hero;

public class method_priority {

  final static Hero gareen = new Hero("gareen", 16, 1);
  final static Hero teemo = new Hero("teemo", 13, 1);
  final static Hero SA = new Hero("SA", 15, 1);
  final static Hero VS = new Hero("VS", 14, 1);

  public static void main(String[] args) {

    method_priority();
//    method_without_priority();
  }

  /**
   * 设置线程优先级 当线程处于竞争关系时, 优先级高的线程会有更大的几率获得CPU资源
   */
  public static void method_priority() {
    System.out.println("===== method_priority start =====");

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
        while (!SA.isDead()) {
          VS.attackHero(SA);
        }
      }
    };

    thread1.setPriority(Thread.MAX_PRIORITY);
    thread2.setPriority(Thread.MIN_PRIORITY);

    thread1.start();
    thread2.start();
  }

  public static void method_without_priority() {
    System.out.println("===== method_without_priority start =====");

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
        while (!SA.isDead()) {
          VS.attackHero(SA);
        }
      }
    };

    thread1.start();
    thread2.start();
  }
}
