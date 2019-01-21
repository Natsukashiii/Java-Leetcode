package com.natsu.threaddemo.threadConcurrency;

import com.natsu.threaddemo.threadConcurrency.Character.Hero;
import com.natsu.threaddemo.threadConcurrency.Character.Hero_Syn;
import com.natsu.threaddemo.threadConcurrency.Character.Hero_Syn_Method;

/**
 * 多线程同步问题, 就是指多个线程同时修改一个数据时, 出现的并发问题 假设gareen有1000hp 在基地恢复hp的同时,也在遭受英雄攻击 多试几次后, 会发现最后得到的值不一定是10000
 */
public class concurrent_attack {

  final static int NUMBER = 10000;

  public static void main(String[] args) {
    attack_example();
    synchronized_object_attack();
    synchronized_hero_attack();
    synchronized_method_attack();
  }


  public static void attack_example() {
    System.out.println("===== attack_example start =====");
    final Hero gareen = new Hero("gareen", NUMBER);
    System.out.println("gareen's hp begins at " + gareen.hp);

    int n = NUMBER;

    Thread[] addThreads = new Thread[n];
    Thread[] reduceThreads = new Thread[n];

    // n个线程增加hp
    for (int i = 0; i < n; i++) {
      Thread thread = new Thread() {
        @Override
        public void run() {
          gareen.recover();
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      };
      thread.start();
      addThreads[i] = thread;
    }

    // n个线程攻击减少hp
    for (int i = 0; i < n; i++) {
      Thread thread = new Thread() {
        @Override
        public void run() {
          gareen.hurt();
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      };
      thread.start();
      reduceThreads[i] = thread;
    }

    //等待所有增加线程结束
    for (Thread thread : addThreads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    //等待所有减少线程结束
    for (Thread thread : reduceThreads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // 此时所有增加和减少线程都结束了
    //观察 gareen 的hp值
    System.out.printf("%d个增加线程和%d个减少线程结束后%ngareen的hp为%.0f%n", n, n, gareen.hp);
    System.out.println("attack_example end... ");
  }

  public static void synchronized_object_attack() {
    System.out.println("===== synchronized_object_attack start =====");

    final Object someObject = new Object();
    final Hero gareen = new Hero("gareen", NUMBER);
    System.out.println("gareen's hp begins at " + gareen.hp);

    int n = NUMBER;

    Thread[] addThreads = new Thread[n];
    Thread[] reduceThreads = new Thread[n];

    // n个线程增加hp
    for (int i = 0; i < n; i++) {
      Thread thread = new Thread() {
        @Override
        public void run() {
          // 任何线程要修改hp的值, 必须先占用someObject
          synchronized (someObject) {
            gareen.recover();
          }
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      };
      thread.start();
      addThreads[i] = thread;
    }

    // n个线程攻击减少hp
    for (int i = 0; i < n; i++) {
      Thread thread = new Thread() {
        @Override
        public void run() {
          //任何线程要修改hp的值，必须先占用someObject
          synchronized (someObject) {
            gareen.hurt();
          }
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      };
      thread.start();
      reduceThreads[i] = thread;
    }

    //等待所有增加线程结束
    for (Thread thread : addThreads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    //等待所有减少线程结束
    for (Thread thread : reduceThreads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // 此时所有增加和减少线程都结束了
    //观察 gareen 的hp值
    System.out.printf("%d个增加线程和%d个减少线程结束后%ngareen的hp为%.0f%n", n, n, gareen.hp);
    System.out.println("synchronized_object_attack end... ");

  }

  public static void synchronized_hero_attack() {
    System.out.println("===== synchronized_hero_attack start =====");

    final Object someObject = new Object();
    final Hero_Syn gareen = new Hero_Syn("gareen", NUMBER);
    System.out.println("gareen's hp begins at " + gareen.hp);

    int n = NUMBER;

    Thread[] addThreads = new Thread[n];
    Thread[] reduceThreads = new Thread[n];

    // n个线程增加hp
    for (int i = 0; i < n; i++) {
      Thread thread = new Thread() {
        @Override
        public void run() {
          // 使用gareen作为synchronized
          synchronized (gareen) {
            gareen.recover();
          }
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      };
      thread.start();
      addThreads[i] = thread;
    }

    // n个线程攻击减少hp
    for (int i = 0; i < n; i++) {
      Thread thread = new Thread() {
        @Override
        public void run() {
          ///使用gareen作为synchronized
          //在方法hurt中有synchronized(this)
          synchronized (gareen) {
            gareen.hurt();
          }
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      };
      thread.start();
      reduceThreads[i] = thread;
    }

    //等待所有增加线程结束
    for (Thread thread : addThreads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    //等待所有减少线程结束
    for (Thread thread : reduceThreads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // 此时所有增加和减少线程都结束了
    //观察 gareen 的hp值
    System.out.printf("%d个增加线程和%d个减少线程结束后%ngareen的hp为%.0f%n", n, n, gareen.hp);
    System.out.println("synchronized_hero_attack end... ");
  }

  public static void synchronized_method_attack() {
    System.out.println("===== synchronized_method_attack start =====");

    final Object someObject = new Object();
    final Hero_Syn_Method gareen = new Hero_Syn_Method("gareen", NUMBER);
    System.out.println("gareen's hp begins at " + gareen.hp);

    int n = NUMBER;

    Thread[] addThreads = new Thread[n];
    Thread[] reduceThreads = new Thread[n];

    // n个线程增加hp
    for (int i = 0; i < n; i++) {
      Thread thread = new Thread() {
        @Override
        public void run() {
          // recover 自带 synchronized
          gareen.recover();
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      };
      thread.start();
      addThreads[i] = thread;
    }

    // n个线程攻击减少hp
    for (int i = 0; i < n; i++) {
      Thread thread = new Thread() {
        @Override
        public void run() {
          //hurt自带synchronized
          gareen.hurt();
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      };
      thread.start();
      reduceThreads[i] = thread;
    }

    //等待所有增加线程结束
    for (Thread thread : addThreads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    //等待所有减少线程结束
    for (Thread thread : reduceThreads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // 此时所有增加和减少线程都结束了
    //观察 gareen 的hp值
    System.out.printf("%d个增加线程和%d个减少线程结束后%ngareen的hp为%.0f%n", n, n, gareen.hp);
    System.out.println("synchronized_method_attack end... ");
  }
}
