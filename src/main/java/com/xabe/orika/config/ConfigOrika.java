package com.xabe.orika.config;

import com.xabe.orika.BeanMapper;
import com.xabe.orika.MapperComponent;
import com.xabe.orika.impl.MapperComponentImpl;
import com.xabe.orika.impl.OrikaBeanMapperImpl;
import ma.glasnost.orika.MapperFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.xabe.orika.mapper"})
public class ConfigOrika {

  @Bean
  public MapperFacade createMapperFacade() {
    return new SpringOrikaBeanMapper();
  }

  @Bean
  public BeanMapper createBeanMapper(final MapperFacade mapperFacade) {
    return new OrikaBeanMapperImpl(mapperFacade);
  }

  @Bean
  public MapperComponent createMapperComponent(final BeanMapper beanMapper) {
    return new MapperComponentImpl(beanMapper);
  }
}