package com.xabe.executor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

  @Override
  public void rejectedExecution(final Runnable r, final ThreadPoolExecutor executor) {
    System.out.println(r.toString() + " is rejected");
  }

}
