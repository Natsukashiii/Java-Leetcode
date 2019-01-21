package com.natsu.threaddemo.threadCreate;

import java.util.concurrent.Callable;

public class Thread3 implements Callable<Integer> {

  private String name;

  public Thread3(String name
  ) {
    this.name = name;
  }

  @Override
  public Integer call() throws Exception {
    int sum = 0;
    for (int i = 0; i <= 100; i++) {
      System.out.println(i);
      sum += i;
    }
    return sum;
  }
}
