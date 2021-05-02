package com.xabe.thread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPool {

  private final LinkedBlockingQueue<Runnable> queue;

  public ThreadPool(int threads) {
    this.queue = new LinkedBlockingQueue<>();
    IntStream.range(0, threads).forEach(i -> new PoolWorker(i).start());
  }

  public void execute(Runnable runnable) {
    synchronized (queue) {
      this.queue.add(runnable);
      this.queue.notify();
    }
  }

  private class PoolWorker extends Thread {

    private final Logger logger = LoggerFactory.getLogger(PoolWorker.class);

    public PoolWorker(int position) {
      this.setName(String.format("PoolWorker-%d", position));
      this.logger.info("Create PoolWorker {}", position);
    }

    @Override
    public void run() {
      Runnable task;
      while (true) {
        synchronized (queue) {
          while (queue.isEmpty()) {
            try {
              queue.wait();
            } catch (InterruptedException e) {
              logger.error("An error occurred while queue is waiting: {}", e.getMessage());
            }
          }
          task = queue.poll();
        }
        // If we don't catch RuntimeException,
        // the pool could leak threads
        try {
          task.run();
        } catch (RuntimeException e) {
          logger.error("Thread pool is interrupted due to an issue: {}", e.getMessage());
        }
      }
    }

  }

}
