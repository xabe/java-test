package com.xabe.http;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

public class HttpTest {

  private final ExecutorService executors = Executors.newFixedThreadPool(10);

  private final String url = "https://api.exchangeratesapi.io/latest?symbols=%s";

  @Test
  public void shouldInvokeSync() throws Exception {
    //Given
    final HttpClient httpClient = this.createHttpClient();
    final var request = this.createHttpRequestGet("https://api.exchangeratesapi.io/latest");

    //When
    final var response = httpClient.send(request, new JsonBodyHandler<>(Exchange.class));

    //Then
    assertThat(response.statusCode(), is(200));

    final Exchange exchange = response.body();

    final List<Exchange> httpResponses =
        exchange.getRates().entrySet().stream().flatMap(this.callSyncRate(httpClient)).collect(Collectors.toList());

    assertThat(httpResponses, is(notNullValue()));
    assertThat(httpResponses, is(hasSize(exchange.getRates().size())));
  }

  private Function<Entry<RateType, Long>, Stream<Exchange>> callSyncRate(final HttpClient httpClient) {
    return (entry) -> {
      try {
        return Stream.of(httpClient
            .send(this.createHttpRequestGet(String.format(this.url, entry.getKey().name())), new JsonBodyHandler<>(Exchange.class)).body());
      } catch (final Exception e) {
        e.printStackTrace();
      }
      return Stream.empty();
    };
  }

  @Test
  public void shouldInvokeAsync() throws Exception {
    //Given
    final HttpClient httpClient = this.createHttpClient();
    final var request = this.createHttpRequestGet("https://api.exchangeratesapi.io/latest");

    //When
    final var response = httpClient.send(request, new JsonBodyHandler<>(Exchange.class));

    //Then
    assertThat(response.statusCode(), is(200));

    final Exchange exchange = response.body();

    final List<CompletableFuture<Exchange>> completableFutures =
        exchange.getRates().entrySet().stream().map(this.callAsyncRate(httpClient)).collect(Collectors.toList());

    final CompletableFuture<List<Exchange>> listCompletableFuture = this.allOfOrException(completableFutures);
    final List<Exchange> httpResponses = listCompletableFuture.get();

    assertThat(httpResponses, is(notNullValue()));
    assertThat(httpResponses, is(hasSize(exchange.getRates().size())));
  }

  private Function<Entry<RateType, Long>, CompletableFuture<Exchange>> callAsyncRate(final HttpClient httpClient) {
    return (entry) ->
        httpClient
            .sendAsync(this.createHttpRequestGet(String.format(this.url, entry.getKey().name())), new JsonBodyHandler<>(Exchange.class))
            .thenApply(HttpResponse::body);
  }

  private HttpClient createHttpClient() {
    return HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)  // this is the default
        .connectTimeout(Duration.ofMillis(5000))
        .followRedirects(HttpClient.Redirect.ALWAYS)
        .executor(this.executors)
        .build();
  }

  private HttpRequest createHttpRequestGet(final String uri) {
    return HttpRequest
        .newBuilder(URI.create(uri))
        .timeout(Duration.ofMillis(5000))
        .GET()
        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .setHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
        .build();
  }

  private <T> CompletableFuture<List<T>> allOfOrException(final Collection<CompletableFuture<T>> futures) {
    final CompletableFuture<List<T>> result = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
        .thenApplyAsync(items -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()), this.executors);

    for (final CompletableFuture<?> f : futures) {
      f.handle((c, ex) -> ex == null || result.completeExceptionally(ex));
    }
    return result;
  }
}
