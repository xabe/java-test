package com.xabe.validatorChain;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Case<T, R> {

  private final Predicate<T> predicate;

  private final Function<T, R> value;

  public Case(final Predicate<T> predicate, final Function<T, R> value) {
    this.predicate = predicate;
    this.value = value;
  }

  public boolean isMatching(final T item) {
    return this.predicate.test(item);
  }

  public R result(final T item) {
    return this.value.apply(item);
  }

  public static <T, R> Case<T, R> matchCase(final Predicate<T> predicate, final Function<T, R> value) {
    return new Case<>(predicate, value);
  }

  public static <T, R> Case<T, R> matchCase(final Function<T, R> value) {
    return new Case<>(item -> true, value);
  }

  public static <T, R> R match(final T item, final Case<T, R> defaultCase, final Case<T, R>... matches) {
    return Stream.of(matches).
        filter(match -> match.isMatching(item)).
        map(match -> match.result(item)).
        findFirst().
        orElseGet(() -> defaultCase.result(item));
  }
}
