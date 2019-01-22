package com.natsu.threaddemo.threadMethod;

import com.natsu.threaddemo.threadMethod.Character.Hero;

public class method_join {

  final static Hero gareen = new Hero("gareen", 616, 15);
  final static Hero temmo = new Hero("temmo", 300, 15);
  final static Hero SA = new Hero("SA", 500, 15);
  final static Hero VS = new Hero("VS", 455, 15);

  public static void main(String[] args) throws InterruptedException {
//    method_join();
//    method_without_join();
    method_join_arg();
  }

  /**
   * 将该线程加入主线程中
   */
  public static void method_join() throws InterruptedException {
    System.out.println("===== method_join start =====");

    Thread t1 = new Thread() {
      @Override
      public void run() {
        while (!temmo.isDead()) {
          gareen.attackHero(temmo);
        }
      }
    };

    t1.start();

    //代码执行到这,一直是main线程在运行
    // t1线程加入到main线程中, 只有t1结束时,才回继续往下走
    t1.join();

    Thread t2 = new Thread() {
      @Override
      public void run() {
        while (!SA.isDead()) {
          VS.attackHero(SA);
        }
      }
    };

    // 盖伦杀掉提莫后, 才会运行t2线程
    t2.start();
  }

  /**
   * 不通过join方法下 线程的运行状态 是交替执行的
   */
  public static void method_without_join() {
    System.out.println("===== method_without_join start =====");

    Thread t1 = new Thread() {
      @Override
      public void run() {
        while (!temmo.isDead()) {
          gareen.attackHero(temmo);
        }
      }
    };

    Thread t2 = new Thread() {
      @Override
      public void run() {
        while (!SA.isDead()) {
          VS.attackHero(SA);
        }
      }
    };

    t1.start();
    t2.start();
  }

  /**
   * 当给join方法传入参数时, 在xx毫秒内, 先执行调用的线程 之后再并行两个线程
   */
  public static void method_join_arg() throws InterruptedException {
    System.out.println("===== method_join_arg start =====");

    Thread t1 = new Thread() {
      @Override
      public void run() {
        for (int i = 0; i < 1000; i++) {
          System.out.println("thread1" + ":" + i);
        }
      }
    };

    Thread t2 = new Thread() {
      @Override
      public void run() {
        for (int i = 0; i < 1000; i++) {
          System.out.println(">>>>>>" + ":" + i);
        }
      }
    };

    t1.start();
    //join方法可以传递参数，join(10)表示main线程会等待t1线程10毫秒，10毫秒过去后，
    //main线程和t1线程之间执行顺序由串行执行变为普通的并行执行
    t1.join(1);
    t2.start();
  }
}
