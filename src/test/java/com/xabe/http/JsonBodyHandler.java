package com.xabe.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class JsonBodyHandler<W> implements HttpResponse.BodyHandler<W> {

  private final Class<W> wClass;

  private final ObjectMapper objectMapper;

  private final HttpResponse.BodySubscriber<String> upstream;

  public JsonBodyHandler(final Class<W> wClass) {
    this.wClass = wClass;
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new JavaTimeModule());
    this.objectMapper.registerModule(new Jdk8Module());
    this.upstream = HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8);
  }

  @Override
  public HttpResponse.BodySubscriber<W> apply(final HttpResponse.ResponseInfo responseInfo) {
    return HttpResponse.BodySubscribers.mapping(
        this.upstream,
        body -> {
          try {
            return this.objectMapper.readValue(body, this.wClass);
          } catch (final IOException e) {
            throw new UncheckedIOException(e);
          }
        });
  }
}