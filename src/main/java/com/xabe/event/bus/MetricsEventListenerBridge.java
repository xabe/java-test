package com.xabe.event.bus;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

public class MetricsEventListenerBridge {

  private final MetricsEventListener metricsEventListener;

  public MetricsEventListenerBridge(final MetricsEventListener metricsEventListener) {
    this.metricsEventListener = metricsEventListener;
  }

  @Async
  @EventListener
  public void asyncRequestHandler(final RequestEvent requestEvent) {
    this.metricsEventListener.onRequest(requestEvent);
  }
}
