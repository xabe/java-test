package com.xabe.event.bus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.spy;

@Configuration
public class MockConfig {
    private Logger logger = LoggerFactory.getLogger(MetricsEventListenerSpringTest.class);

    @Bean
    public MetricsEventListener metricsEventListener(){
        return spy(new MetricsEventListener() {
            @Override
            public void onRequest(RequestEvent requestEvent) {
                logger.info(requestEvent.toString());
            }
        });
    }
}
