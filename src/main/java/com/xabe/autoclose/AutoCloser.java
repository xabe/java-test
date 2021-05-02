package com.xabe.autoclose;

import java.util.function.Consumer;

public class AutoCloser<T> {

  private final T resource;

  public AutoCloser(final T resource) {
    this.resource = resource;
  }

  public AutoClosableSupplier closeWith(final Consumer<T> closer) {
    return new AutoClosableSupplier(this.resource, closer);
  }

  public static <T> AutoCloser<T> useResource(final T notAutoclosable) {
    return new AutoCloser<T>(notAutoclosable);
  }
}
