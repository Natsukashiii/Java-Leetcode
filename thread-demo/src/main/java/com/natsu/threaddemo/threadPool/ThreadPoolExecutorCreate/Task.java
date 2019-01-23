package com.natsu.threaddemo.threadPool.ThreadPoolExecutorCreate;

public class Task implements Runnable {
 private String taskId;
  private String taskName;

  public Task(String taskId, String taskName) {
    this.taskId = taskId;
    this.taskName = taskName;
  }

  @Override
  public void run() {
    try {
      System.out.println("taskName: "+taskName+" 正在执行");
      Thread.sleep(2000);
      System.out.println("taskName: " + taskName + " 执行结束");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String toString() {
    return taskName.toString();
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }
}
