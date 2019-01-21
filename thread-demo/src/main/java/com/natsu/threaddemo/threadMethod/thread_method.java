package com.natsu.threaddemo.threadMethod;

import com.natsu.threaddemo.threadMethod.Character.Hero;

public class thread_method {

  public static void main(String[] args) {
    method_sleep();

    method_join();
    method_without_join();

    method_priority();
    method_without_priority();

    method_yield();

    thread_all_daemon();
    thread_not_all_daemon();

  }

  public static void method_sleep() {
    System.out.println("===== method_sleep start =====");

    Thread thread=new Thread(){
      @Override
      public void run() {
        int seconds = 0;
        while (true){
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.printf("playing %d s%n",seconds++);
        }
      }
    };
    thread.start();
    System.out.println("method_sleep end...");
  }

  public static void method_join() {
    System.out.println("===== method_join start =====");

    final Hero gareen = new Hero("gareen", 616, 50);
    final Hero temmo = new Hero("temmo", 300, 30);
    final Hero SA = new Hero("SA", 500, 65);
    final Hero VS = new Hero("VS", 455, 80);

    Thread t1 = new Thread(){
      @Override
      public void run() {
        while (!temmo.isDead()) {
          gareen.attackHero(temmo);
        }
      }
    };

    t1.start();

    //代码执行到这,一直是main线程在运行
    try {
      // t1线程加入到main线程中, 只有t1结束时,才回继续往下走
      t1.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Thread t2=new Thread(){
      @Override
      public void run() {
        while (!SA.isDead()){
          VS.attackHero(SA);
        }
      }
    };

    // 盖伦杀掉提莫后, 才会运行t2线程
    t2.start();
    System.out.println("method_join end...");
  }

  public static void method_without_join() {
    System.out.println("===== method_without_join start =====");

    final Hero gareen = new Hero("gareen", 616, 50);
    final Hero temmo = new Hero("temmo", 300, 30);
    final Hero SA = new Hero("SA", 500, 65);
    final Hero VS = new Hero("VS", 455, 80);

    Thread t1 = new Thread(){
      @Override
      public void run() {
        while (!temmo.isDead()) {
          gareen.attackHero(temmo);
        }
      }
    };

    t1.start();

//    //代码执行到这,一直是main线程在运行
//    try {
//      // t1线程加入到main线程中, 只有t1结束时,才回继续往下走
//      t1.join();
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }

    Thread t2=new Thread(){
      @Override
      public void run() {
        while (!SA.isDead()){
          VS.attackHero(SA);
        }
      }
    };

    t2.start();
    System.out.println("method_without_join end...");
  }

  public static void method_priority() {
    System.out.println("===== method_priority start =====");

    final Hero gareen = new Hero("gareen", 16, 1);
    final Hero teemo = new Hero("teemo", 13, 1);
    final Hero SA = new Hero("SA", 15, 1);
    final Hero VS = new Hero("VS", 14, 1);

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
    System.out.println("method_priority end...");
  }

  public static void method_without_priority() {
    System.out.println("===== method_without_priority start =====");

    final Hero gareen = new Hero("gareen", 16, 1);
    final Hero teemo = new Hero("teemo", 13, 1);
    final Hero SA = new Hero("SA", 15, 1);
    final Hero VS = new Hero("VS", 14, 1);

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
    System.out.println("method_without_priority end...");

  }

  public static void method_yield() {
    System.out.println("===== method_yield start =====");

    final Hero gareen = new Hero("gareen", 16, 1);
    final Hero teemo = new Hero("teemo", 13, 1);
    final Hero SA = new Hero("SA", 15, 1);
    final Hero VS = new Hero("VS", 15, 1);

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
    System.out.println("method_yield end...");

  }

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
    System.out.println("thread_all_daemon end...");
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
//    thread2.start();
    System.out.println("thread_not_all_daemon end...");
  }

}
