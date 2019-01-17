package com.natsu.threaddemo.threadPoolExample;

public class ThreadExample {

  public static void main(String[] args) {
    test02();
  }

  public static void test01() {
    ThreadPool pool = new ThreadPool();
    for (int i = 0; i < 5; i++) {
      Runnable task = new Runnable() {
        @Override
        public void run() {
          System.out.println(" execute ");
        }
      };
      pool.add(task);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 每个任务执行时间都是1s, 刚开始时间是
   */
  public static void test02(){
    ThreadPool pool= new ThreadPool();
    int sleep=1000;
    while(true){
      pool.add(new Runnable(){
        @Override
        public void run() {
          //System.out.println("执行任务");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      });
      try {
        Thread.sleep(sleep);
        sleep = sleep>100?sleep-100:sleep;
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
  }
}
