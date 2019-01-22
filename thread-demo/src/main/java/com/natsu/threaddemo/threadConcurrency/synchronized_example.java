package com.natsu.threaddemo.threadConcurrency;

import java.text.SimpleDateFormat;
import java.util.Date;

public class synchronized_example {

  public static void main(String[] args) {
    synchronized_example_test();
  }

  public static void synchronized_example_test() {
    final Object someObject = new Object();

    Thread thread1 = new Thread() {
      @Override
      public void run() {
        try {
          System.out.println(">>> " + now() + ": thread1 is running...");
          System.out.println(this.getName() + ": is trying to occupy [someObject]");
          synchronized (someObject) {
            System.out.println(now() + ": " + this.getName() + ": occupy [someObject]");
            Thread.sleep(2000);
            System.out.println(now() + ": " + this.getName() + ": release [someObject]");
          }
          System.out.println(">>> " + now() + ": thread1 is end...");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    thread1.setName("thread1");
    thread1.start();

    Thread thread2 = new Thread() {
      @Override
      public void run() {
        try {
          System.out.println(">>> " + now() + ": thread2 is running...");
          System.out.println(this.getName() + ": is trying to occupy [someObject]");
          synchronized (someObject) {
            System.out.println(now() + ": " + this.getName() + ": occupy [someObject]");
            Thread.sleep(2000);
            System.out.println(now() + ": " + this.getName() + ": release [someObject]");
          }
          System.out.println(">>> " + now() + ": thread2 is end...");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    thread2.setName("thread2");
    thread2.start();
  }

  public static String now() {
    return new SimpleDateFormat("HH:mm:ss").format(new Date());
  }
}
