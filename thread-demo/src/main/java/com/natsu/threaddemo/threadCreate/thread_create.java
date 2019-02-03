package com.natsu.threaddemo.threadCreate;

import com.natsu.threaddemo.threadCreate.Character.Hero;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class thread_create {

  final static Hero gareen = new Hero("gareen", 616, 50);
  final static Hero teemo = new Hero("teemo", 300, 30);
  final static Hero SA = new Hero("SA", 500, 65);
  final static Hero VS = new Hero("VS", 455, 80);

  public static void main(String[] args) {
    attack_without_thread();
//    attack_with_thread();
//    attack_with_runnable();
//    attack_with_callable();
//    attack_with_anonymous_class();
  }

  /**
   * 不使用线程的情况 只有gareen 杀死 teemo后 , VS 才会开始攻击 SA
   */
  public static void attack_without_thread() {
    System.out.println("===== attack_without_thread start =====");

    // gareen attack teemo
    while (!teemo.isDead()) {
      gareen.attackHero(teemo);
    }

    // 执行完上面的 while 循环后, 才会开始执行下面的循环
    // SA attack VS
    while (!VS.isDead()) {
      SA.attackHero(VS);
    }
    System.out.println("attack_without_thread end...");
  }

  /**
   * 继承Thread类的方式创建线程 两个攻击方法是交替进行的
   */
  public static void attack_with_thread() {
    System.out.println("===== attack_with_thread start =====");

    Kill_Thread kill_thread1 = new Kill_Thread(gareen, teemo);
    Kill_Thread kill_thread2 = new Kill_Thread(VS, SA);
    kill_thread1.start();
    kill_thread2.start();

    System.out.println("attack_with_thread end...");
  }

  /**
   * 实现Runnable接口的创建方式
   */
  public static void attack_with_runnable() {
    System.out.println("===== attack_with_runnable start =====");

    // 0.先创建一个Kill_Runnable(实现了Runnable接口)对象
    Kill_Runnable kill_runnable_1 = new Kill_Runnable(gareen, teemo);
    Kill_Runnable kill_runnable_2 = new Kill_Runnable(SA, VS);
    // 1.再根据Kill_Runnable对象创建一个线程对象, 并启动
    new Thread(kill_runnable_1).start();
    new Thread(kill_runnable_2).start();

    System.out.println("attack_with_runnable end...");
  }

  /**
   * 使用Callable接口的方式创建线程 相较于Runnable接口的方式, 方法可以有返回值, 并且可以抛出异常
   */
  public static void attack_with_callable() {
    System.out.println("===== attack_with_callable start =====");
    int attack_times = 0;

    // 0.先创建一个Kill_Callable(实现了Callable接口)对象
    Kill_Callable kill_callable1 = new Kill_Callable(gareen, teemo);
    // 1. 需要FutureTask类的支持用于接收运算结果
    FutureTask<Integer> result = new FutureTask<>(kill_callable1);
    // 2. 创建一个线程对象,并启动
    new Thread(result).start();

    try {
      // 3. 接收线程结果
      attack_times = result.get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }

    System.out.println("attack_times: " + attack_times);
    System.out.println("attack_with_callable end...");
  }

  /**
   * 使用匿名类的方式创建线程 继承Thread类, 重写run方法
   */
  public static void attack_with_anonymous_class() {
    System.out.println("===== attack_with_anonymous_class start =====");

    // 匿名类
    Thread thread1 = new Thread() {
      @Override
      //重写run方法
      public void run() {
        // 匿名类中用到外部的局部变量teemo, 需要把teemo声明为final
        // JDK7之后,就不必须加final了
        while (!teemo.isDead()) {
          gareen.attackHero(teemo);
        }
      }
    };
    thread1.start();

    Thread thread2 = new Thread() {
      @Override
      public void run() {
        while (!SA.isDead()) {
          VS.attackHero(SA);
        }
      }
    };
    thread2.start();

    System.out.println("attack_with_anonymous_class end...");
  }


}
