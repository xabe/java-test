package com.xabe.monad;

import java.util.Objects;
import java.util.function.Function;

public final class ResultOrError<R, E> {

  private final R result;

  private final E error;

  private ResultOrError(final R result, final E error) {
    this.result = result;
    this.error = error;
  }

  public static <R, E> ResultOrError<R, E> createResult(final R result) {
    return new ResultOrError<>(result, null);
  }

  public static <R, E> ResultOrError<R, E> createError(final E error) {
    return new ResultOrError<>(null, error);
  }

  public R getResult() {
    return this.result;
  }

  public E getError() {
    return this.error;
  }

  public boolean isResult() {
    return Objects.nonNull(this.result);
  }

  public <T> ResultOrError<T, E> bind(final Function<R, ResultOrError<T, E>> function) {
    if (this.isResult()) {
      return function.apply(this.result);
    } else {
      return (ResultOrError<T, E>) this;
    }
  }


}
