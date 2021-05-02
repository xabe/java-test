package com.xabe.event.bus;

import java.util.concurrent.CompletableFuture;

public class MetricsEmitter {

  private final MetricsEventPublisher metricsEventPublisher;

  public MetricsEmitter(final MetricsEventPublisher metricsEventPublisher) {
    this.metricsEventPublisher = metricsEventPublisher;
  }

  public CompletableFuture<Void> emitRequestEvent(final Request request) {
    return this.metricsEventPublisher.publish(RequestEvent.of(request));
  }
}
