package com.xabe.event.bus;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MetricsEventPublisherImplTest {

    private ApplicationEventPublisher applicationEventPublisher;
    private MetricsEventPublisher metricsEventPublisher;

    @BeforeEach
    public void setUp() throws Exception {
        this.applicationEventPublisher = mock(ApplicationEventPublisher.class);
        this.metricsEventPublisher = new MetricsEventPublisherImpl(applicationEventPublisher);
    }

    @Test
    public void givenARequestEventWhenInvokePublishThenReturncompletableFuture() throws Exception {
        //Given
        final RequestEvent requestEvent = RequestEvent.of("event", LocalDateTime.now());

        //When
        final CompletableFuture<Void> publish = this.metricsEventPublisher.publish(requestEvent);

        //Then
        MatcherAssert.assertThat(publish, Matchers.is(Matchers.notNullValue()));
        MatcherAssert.assertThat(publish.join(), Matchers.is(Matchers.nullValue()));
        verify(applicationEventPublisher,timeout(100)).publishEvent(any(RequestEvent.class));
    }
}
