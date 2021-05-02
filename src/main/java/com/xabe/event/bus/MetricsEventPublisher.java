package com.xabe.event.bus;

import java.util.concurrent.CompletableFuture;

public interface MetricsEventPublisher {

  CompletableFuture<Void> publish(RequestEvent requestEvent);
}
