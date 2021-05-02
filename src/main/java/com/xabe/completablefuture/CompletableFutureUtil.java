package com.xabe.completablefuture;

import io.vavr.Tuple2;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CompletableFutureUtil {

  public static <T> CompletableFuture<List<T>> sequence(final List<CompletableFuture<T>> futures, final ExecutorService executorService) {
    final CompletableFuture<List<T>> result = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
        .thenApplyAsync(__ -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()), executorService);

    for (final CompletableFuture<?> f : futures) {
      f.handle((c, ex) -> ex == null || result.completeExceptionally(ex));
    }
    return result;
  }

  public static <T, R> CompletableFuture<T> fold(final List<CompletableFuture<T>> futures, final R identity,
      final BiFunction<R, T, R> accumulator, final ExecutorService executorService) {
    final CompletableFuture<List<T>> result = sequence(futures, executorService);
    return (CompletableFuture<T>) result.thenApplyAsync(items -> items.stream().reduce(identity, accumulator, (a, b) -> a));
  }

  public static <T, S> CompletableFuture<Tuple2<T, S>> zip(final CompletableFuture<T> completableFuture1,
      final CompletableFuture<S> completableFuture2, final ExecutorService executorService) {
    return completableFuture1.thenCombineAsync(completableFuture2, Tuple2::new, executorService);
  }

  public static <T, S> CompletableFuture<S> flatMap(final CompletableFuture<T> completableFuture,
      final Function<T, CompletableFuture<S>> fn, final ExecutorService executorService) {
    return completableFuture.thenComposeAsync(fn, executorService);
  }

  public static <T, D> CompletableFuture<D> map(final CompletableFuture<T> completableFuture,
      final Function<T, D> fn, final ExecutorService executorService) {
    return completableFuture.thenApplyAsync(fn, executorService);
  }

  public static <T> CompletableFuture<T> retry(final Supplier<CompletableFuture<T>> completableFutureSupplier, final int maxRetries,
      final ExecutorService executorService) {
    return completableFutureSupplier.get().handleAsync((value, failure) -> {
      if (failure == null) {
        return CompletableFuture.completedFuture(value);
      } else if (maxRetries > 0) {
        return retry(completableFutureSupplier, maxRetries - 1, executorService);
      } else {
        throw new RuntimeException("Reached max retries", failure);
      }

    }, executorService).thenCompose(Function.identity());
  }
}
