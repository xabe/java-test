package com.xabe.event.bus;

import org.springframework.context.ApplicationEventPublisher;

import java.util.concurrent.CompletableFuture;

public class MetricsEventPublisherImpl implements MetricsEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public MetricsEventPublisherImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public CompletableFuture<Void> publish(RequestEvent requestEvent) {
        return CompletableFuture.supplyAsync(() -> {
            applicationEventPublisher.publishEvent(requestEvent);
            return null;
        });
    }
}
