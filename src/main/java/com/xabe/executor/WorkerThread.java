package com.xabe.executor;

public class WorkerThread implements Runnable {

  private final String command;

  public WorkerThread(final String s) {
    this.command = s;
  }

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " Start. Command = " + this.command);
    this.processCommand();
    System.out.println(Thread.currentThread().getName() + " End.");
  }

  private void processCommand() {
    try {
      Thread.sleep(5000);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String toString() {
    return this.command;
  }
}
