package com.xabe.event.bus;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MetricsEmitterTest {

    private MetricsEventPublisher metricsEventPublisher;
    private MetricsEmitter metricsEmitter;


    @BeforeEach
    public void setUp() throws Exception {
        this.metricsEventPublisher = mock(MetricsEventPublisher.class);
        this.metricsEmitter =  new MetricsEmitter(metricsEventPublisher);
    }
    
    @Test
    public void shouldSentEventWhenInvokeEmitRequestEvent() throws Exception {
        //Given
        final Request request = Request.of("name", LocalDateTime.now());
        when(metricsEventPublisher.publish(any(RequestEvent.class))).thenReturn(CompletableFuture.completedFuture(null));

        //When
        final CompletableFuture<Void> completableFuture = this.metricsEmitter.emitRequestEvent(request);


        //Then
        MatcherAssert.assertThat(completableFuture.join(), Matchers.is(Matchers.nullValue()));
    }

    @Test
    public void shouldGetCompletableFutureWithException() throws Exception {
        //Given
        final Request request = Request.of("name", LocalDateTime.now());
        when(metricsEventPublisher.publish(any(RequestEvent.class))).thenReturn(CompletableFuture.failedFuture(new RuntimeException()));

        //When
        final CompletableFuture<Void> completableFuture = this.metricsEmitter.emitRequestEvent(request);


        //Then
        MatcherAssert.assertThat(completableFuture.isCompletedExceptionally(), Matchers.is(true));
        Assertions.assertThrows(RuntimeException.class, () -> completableFuture.join());
    }
}
