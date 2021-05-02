package com.xabe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(factory = TypesafePropertySourceFactory.class, value = "application.conf")
public class TestConfig {

}
