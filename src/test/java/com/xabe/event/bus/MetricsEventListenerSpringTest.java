package com.xabe.event.bus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { EventConfig.class, MockConfig.class})
public class MetricsEventListenerSpringTest {

    @Autowired
    private MetricsEventListener metricsEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    public void givenARequestEventWhenInvokePublishThen() throws Exception {
        //Given
        final RequestEvent requestEvent = RequestEvent.of("error", LocalDateTime.now());

        //When
        this.applicationEventPublisher.publishEvent(requestEvent);

        //Then
        verify(metricsEventListener,timeout(100)).onRequest(any(RequestEvent.class));

    }
}
