package com.xabe.event.bus;

import java.util.concurrent.Executor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class EventConfig extends AsyncConfigurerSupport {

  @Override
  public Executor getAsyncExecutor() {
    final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(10);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix("event-bus-");
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.initialize();
    return executor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return new SimpleAsyncUncaughtExceptionHandler();
  }

  @Bean
  public MetricsEventListenerBridge metricsEventListenerBridge(final MetricsEventListener metricsEventListener) {
    return new MetricsEventListenerBridge(metricsEventListener);
  }
}
