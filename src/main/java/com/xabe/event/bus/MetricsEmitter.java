package com.xabe.event.bus;

import java.util.concurrent.CompletableFuture;

public class MetricsEmitter {

    private final MetricsEventPublisher metricsEventPublisher;

    public MetricsEmitter(MetricsEventPublisher metricsEventPublisher) {
        this.metricsEventPublisher = metricsEventPublisher;
    }

    public CompletableFuture<Void> emitRequestEvent(Request request) {
        return this.metricsEventPublisher.publish(RequestEvent.of(request));
    }
}
