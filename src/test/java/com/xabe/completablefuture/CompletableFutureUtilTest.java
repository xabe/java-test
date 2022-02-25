package com.xabe.completablefuture;

import static java.util.stream.Collectors.toList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.vavr.Tuple2;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class CompletableFutureUtilTest {

  private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);

  @Test
  @Timeout(10)
  public void exampleParallelOrderedShortCircuiting() throws Exception {
    final List<CompletableFuture<Integer>> completableFutures =
        IntStream.range(0, 10).boxed().map(i -> CompletableFuture.supplyAsync(() -> {
          if (i != 9) {
            try {
              TimeUnit.SECONDS.sleep(10);
            } catch (final InterruptedException e) {
              throw new RuntimeException(e);
            }
            return i;
          } else {
            throw new RuntimeException();
          }
        }, EXECUTOR)).collect(toList());

    assertThrows(RuntimeException.class, () -> CompletableFutureUtil.sequence(completableFutures, EXECUTOR).join());
  }

  @Test
  @Timeout(5)
  public void exampleParallelOrderedWithoutErrorsShortCircuiting() throws Exception {
    final List<CompletableFuture<Integer>> completableFutures =
        IntStream.range(0, 10).boxed().map(i -> CompletableFuture.supplyAsync(() -> {
          if (i != 9) {
            try {
              TimeUnit.MILLISECONDS.sleep(100);
            } catch (final InterruptedException e) {
              throw new RuntimeException(e);
            }
            return i;
          } else {
            throw new RuntimeException();
          }
        }, EXECUTOR)).collect(toList());

    final List<Integer> result = CompletableFutureUtil.sequenceIgnoreFailure(completableFutures, EXECUTOR).join();

    assertThat(result, is(notNullValue()));
    assertThat(result, is(hasSize(9)));
  }

  @Test
  @Timeout(2)
  public void exampleParallelOrdered() throws Exception {
    final List<CompletableFuture<Integer>> completableFutures =
        IntStream.range(0, 10).boxed().map(i -> CompletableFuture.supplyAsync(() -> {
          try {
            TimeUnit.MILLISECONDS.sleep(100);
          } catch (final InterruptedException e) {
            throw new RuntimeException(e);
          }
          return i;
        }, EXECUTOR)).collect(toList());

    final List<Integer> result = CompletableFutureUtil.sequence(completableFutures, EXECUTOR).join();

    assertThat(result, is(notNullValue()));
    assertThat(result, is(hasSize(10)));
  }

  @Test
  public void givenAListCompletableFutureWhenInvokeSequenceThenReturnCompletableFutureList() throws Exception {
    //Given
    final List<CompletableFuture<String>> futures =
        Arrays.asList(CompletableFuture.supplyAsync(() -> "Hola"), CompletableFuture.supplyAsync(() -> "Adios"));

    //When
    final CompletableFuture<List<String>> result = CompletableFutureUtil.sequence(futures, EXECUTOR);

    //Then
    Awaitility.await().until(() -> {
      assertThat(result.join(), is(hasSize(2)));
      return true;
    });
  }

  @Test
  public void givenAListCompletableFutureWhenInvokeFoldThenReturnCompletableFutute() throws Exception {
    //Given
    final List<CompletableFuture<Integer>> futures =
        Stream.iterate(10, i -> i - 1).limit(10).map(i -> CompletableFuture.supplyAsync(() -> i)).collect(toList());

    //When
    final CompletableFuture<Integer> result =
        CompletableFutureUtil.fold(futures, 0, (acumulador, item) -> acumulador += item, EXECUTOR);

    //Then
    Awaitility.await().until(() -> {
      assertThat(result.join(), is(55));
      return true;
    });
  }

  @Test
  public void givenATwoCompletableFutureWhenInvokeZipThenReturnCompletableFutureTuple2() throws Exception {
    //Given
    final CompletableFuture<Integer> completableFutureInteger = CompletableFuture.supplyAsync(() -> 1);
    final CompletableFuture<String> completableFutureString = CompletableFuture.supplyAsync(() -> "Hola");

    //Then
    final CompletableFuture<Tuple2<Integer, String>> completableFutureZip =
        CompletableFutureUtil.zip(completableFutureInteger, completableFutureString, EXECUTOR);

    //When
    Awaitility.await().until(() -> {
      assertThat(completableFutureZip.join(), is(notNullValue()));
      return true;
    });

  }

  @Test
  public void givenACompletableFutureWhenInvokeFlatMapThenReturnCompletableFuture() throws Exception {
    //Given
    final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(this.getString());

    //When
    final CompletableFuture<Integer> result =
        CompletableFutureUtil.flatMap(completableFuture, item -> CompletableFuture.supplyAsync(() -> item.length()), EXECUTOR);

    //Then
    Awaitility.await().until(() -> {
      assertThat(result.join(), is(notNullValue()));
      return true;
    });
  }

  @Test
  public void givenACompletableFutureWhenInvokeMapThenReturnCompletableFuture() throws Exception {
    //Given
    final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(this.getString());

    //When
    final CompletableFuture<Integer> result = CompletableFutureUtil.map(completableFuture, String::length, EXECUTOR);

    //Then
    Awaitility.await().until(() -> {
      assertThat(result.join(), is(notNullValue()));
      return true;
    });
  }

  @Test
  public void givenACompletableFutureWhenInvokeRetryThenReturnCompletableFuture() throws Exception {
    //Given
    final Supplier<String> supplier = mock(Supplier.class);
    final Supplier<CompletableFuture<String>> completableFuturesSupplier = () -> CompletableFuture.supplyAsync(() -> supplier.get());

    when(supplier.get()).thenThrow(new RuntimeException()).thenReturn("HI");
    //When
    final CompletableFuture<String> result = CompletableFutureUtil.retry(completableFuturesSupplier, 3, EXECUTOR);

    //Then
    verify(supplier, timeout(200).atLeast(2)).get();
    Awaitility.await().until(() -> {
      assertThat(result.join(), is(notNullValue()));
      return true;
    });
  }

  @Test
  public void givenACompletableFutureWhenInvokeRetryThenReturnCompletableFutureError() throws Exception {
    //Given
    final Supplier<String> supplier = mock(Supplier.class);
    final Supplier<CompletableFuture<String>> completableFuturesSupplier = () -> CompletableFuture.supplyAsync(() -> supplier.get());

    when(supplier.get()).thenThrow(new RuntimeException()).thenThrow(new RuntimeException());
    //When

    assertThrows(CompletionException.class, () -> {
      CompletableFutureUtil.retry(completableFuturesSupplier, 1, EXECUTOR).join();
    });
  }

  private SupplierException<String> getString() {
    return () -> {
      TimeUnit.SECONDS.sleep(3);
      return "Hello";
    };
  }

  private SupplierException<String> getException() {
    return () -> {
      throw new RuntimeException("Error Custom");
    };
  }

  public interface SupplierException<T> extends Supplier<T> {

    @Override
    default T get() {
      try {
        return this.getException();
      } catch (final Exception e) {
        throw new RuntimeException(e);
      }
    }

    T getException() throws Exception;
  }

}
