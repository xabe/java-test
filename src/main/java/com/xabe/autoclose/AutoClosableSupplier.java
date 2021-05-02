package com.xabe.autoclose;

import java.util.function.Consumer;

public class AutoClosableSupplier<T> implements AutoCloseable {

  private final T resource;

  private final Consumer<T> closer;

  AutoClosableSupplier(final T resource, final Consumer<T> closer) {
    this.resource = resource;
    this.closer = closer;
  }

  @Override
  public void close() {
    this.closer.accept(this.resource);
  }
}
