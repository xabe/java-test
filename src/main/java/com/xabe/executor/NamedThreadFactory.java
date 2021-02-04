package com.xabe.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {

  private final String name;

  private int count;

  public NamedThreadFactory(final String name) {
    this.name = name;
  }

  @Override
  public Thread newThread(final Runnable r) {
    this.count++;
    final ThreadFactory factory = Executors.defaultThreadFactory();
    final Thread t = factory.newThread(r);
    t.setName(this.name + "-" + this.count);
    System.out.println("New thread " + t);
    return t;
  }
}
