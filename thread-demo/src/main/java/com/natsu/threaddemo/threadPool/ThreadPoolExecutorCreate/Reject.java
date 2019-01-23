package com.natsu.threaddemo.threadPool.ThreadPoolExecutorCreate;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class Reject implements RejectedExecutionHandler {

  public Reject() {

  }
  @Override
  public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
    System.out.println("被拒绝的任务: " + r.toString());
  }
}
