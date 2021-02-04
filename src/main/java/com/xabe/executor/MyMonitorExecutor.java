package com.xabe.executor;

import java.util.concurrent.ThreadPoolExecutor;

public class MyMonitorExecutor implements Runnable {

  private final ThreadPoolExecutor executor;

  public MyMonitorExecutor(final ThreadPoolExecutor executor) {
    this.executor = executor;
  }

  @Override
  public void run() {
    System.out.println(
        String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
            this.executor.getPoolSize(),
            this.executor.getCorePoolSize(),
            this.executor.getActiveCount(),
            this.executor.getCompletedTaskCount(),
            this.executor.getTaskCount(),
            this.executor.isShutdown(),
            this.executor.isTerminated()));

  }
}
