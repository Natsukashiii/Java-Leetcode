package com.natsu.threaddemo.threadCreate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {

  public static void main(String[] args) {
    implementCallable();
  }

  public static void extendThread() {
    Thread1 mTh1 = new Thread1("A");
    Thread1 mTh2 = new Thread1("B");
    // run()是封装被执行的方法,直接调用时属于普通方法
    // start()首先启动了线程,再去调用线程的run方法
    mTh1.start();
    mTh2.start();
  }

  public static void implementRunnable() {
    new Thread(new Thread2("C")).start();
    new Thread(new Thread2("D")).start();
  }

  public static void implementCallable() {
    Thread3 thread3 = new Thread3("E");

    // 1.执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果
    FutureTask<Integer> result = new FutureTask<>(thread3);
    new Thread(result).start();

    // 2.接收线程运算后的结果
    Integer sum;
    try {
      //等所有线程执行完，获取值，因此FutureTask 可用于 闭锁
      sum = result.get();
      System.out.println("-----------------------------");
      System.out.println(sum);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

}
