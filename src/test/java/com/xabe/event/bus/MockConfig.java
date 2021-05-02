package com.xabe.event.bus;

import static org.mockito.Mockito.spy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockConfig {

  private final Logger logger = LoggerFactory.getLogger(MetricsEventListenerSpringTest.class);

  @Bean
  public MetricsEventListener metricsEventListener() {
    return spy(new MetricsEventListener() {
      @Override
      public void onRequest(final RequestEvent requestEvent) {
        MockConfig.this.logger.info(requestEvent.toString());
      }
    });
  }
}
