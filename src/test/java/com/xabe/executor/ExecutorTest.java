package com.xabe.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class ExecutorTest {

  @Test
  public void shouldExecute() throws Exception {
    //Given
    final RejectedExecutionHandler rejectionHandler = new RejectedExecutionHandlerImpl();
    final ThreadFactory threadFactory = new NamedThreadFactory("executor1");
    final ThreadPoolExecutor executorPool =
        new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);

    final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    scheduledExecutorService.scheduleWithFixedDelay(new MyMonitorExecutor(executorPool), 0, 1, TimeUnit.SECONDS);

    for (int i = 0; i < 10; i++) {
      executorPool.execute(new WorkerThread("cmd" + i));
    }
    executorPool.awaitTermination(15, TimeUnit.SECONDS);
    executorPool.shutdown();
    scheduledExecutorService.shutdown();
  }

}
